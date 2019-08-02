package com.github.travelervihaan.clubmanagement.service.workdiagram;

import com.github.travelervihaan.clubmanagement.model.workdiagram.WorkDay;
import com.github.travelervihaan.clubmanagement.model.workdiagram.WorkDayImportance;
import com.github.travelervihaan.clubmanagement.repository.workdiagram.WorkDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class WorkDayService {

    private WorkDayRepository workDayRepository;
    private WorkDayImportanceService workDayImportanceService;
    private WorkDayDetailsService workDayDetailsService;

    @Autowired
    public WorkDayService(WorkDayRepository workDayRepository,
                          WorkDayImportanceService workDayImportanceService,
                          WorkDayDetailsService workDayDetailsService){
        this.workDayRepository = workDayRepository;
        this.workDayImportanceService = workDayImportanceService;
        this.workDayDetailsService = workDayDetailsService;
    }

    public Optional<WorkDay> getWorkDayById(Long id){
        return workDayRepository.findById(id);
    }

    public Optional<WorkDay> getOneWorkDayByDate(LocalDate date){
        return workDayRepository.findByDate(date);
    }

    public List<WorkDayImportance> getAllImportanceLevels(){
        return workDayImportanceService.getAllWorkDayImportanceLevels();
    }

    public Optional<WorkDay> getNewestWorkDay(){
        return Optional.ofNullable(workDayRepository.findTopByOrderByIdDesc());
    }

    public List<WorkDay> getWorkDaysInCurrentMonth(){
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.withDayOfMonth(1).minusDays(1);
        LocalDate endDate = today.withDayOfMonth(today.lengthOfMonth()).plusDays(1);
        return workDayRepository.findByDateBetween(startDate,endDate);

    }

    public void createDefaultWorkDay(LocalDate date){
        workDayRepository.save(this.getDefaultWorkDay(date));
    }

    public WorkDay getDefaultWorkDay(LocalDate date){
        WorkDay workDay = new WorkDay();
        workDay.setDate(date);
        workDay.setWorkDayImportance(workDayImportanceService.getDefaultImportanceLevel());
        workDay.setWorkingTime(8);
        return workDay;
    }

    public void addEmployeeToWorkDay(Long workDayId, String employeeUsername){
        workDayRepository.findById(workDayId).ifPresent(workDay -> workDayDetailsService.addEmployeeToWorkDay(workDay, employeeUsername));
    }

    public void dropEmployeeFromWorkDay(Long workDayId, String employeeUsername){
        workDayRepository.findById(workDayId).ifPresent(workDay -> workDayDetailsService.deleteEmployeeToWorkDay(workDay, employeeUsername));
    }

    public void setWorkTime(Long workDayId, int workTime){
        workDayRepository.findById(workDayId).ifPresent(workDay -> workDayDetailsService.saveWorkTime(workDay, workTime));
    }

    public void setBookedArtist(Long workDayId, String artist){
        workDayRepository.findById(workDayId).ifPresent(workDay -> workDayDetailsService.saveBookedArtist(workDay, artist));
    }

    public void setImportanceLevel(Long workDayId, int workDayImportance){
        workDayRepository
                .findById(workDayId)
                .ifPresent(workDay -> workDayDetailsService
                        .saveImportanceLevel(workDay,workDayImportanceService
                                .getWorkDayImportanceByLevel(workDayImportance)
                                .orElse(workDayImportanceService.getDefaultImportanceLevel())));
    }

}
