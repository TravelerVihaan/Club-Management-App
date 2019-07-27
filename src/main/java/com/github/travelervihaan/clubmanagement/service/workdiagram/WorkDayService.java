package com.github.travelervihaan.clubmanagement.service.workdiagram;

import com.github.travelervihaan.clubmanagement.model.employers.Employee;
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

    public void setWorkTime(Long workDayId, int workTime){
        workDayRepository.findById(workDayId).ifPresent(workDay -> workDayDetailsService.saveWorkTime(workDay, workTime));
    }

    public void setBookedArtist(Long workDayId, String artist){
        workDayRepository.findById(workDayId).ifPresent(workDay -> workDayDetailsService.saveBookedArtist(workDay, artist));
    }

    public void setImportanceLevel(Long workDayId, WorkDayImportance workDayImportance){
        workDayRepository.findById(workDayId).ifPresent(workDay -> workDayDetailsService.saveImportanceLevel(workDay,workDayImportance));
    }

    public boolean addEmployeeToWorkDay(WorkDay workDay, Employee employee){
        if(workDay.getEmployers().size() < workDay.getWorkDayImportance().getImportanceLevel()) {
            workDay.getEmployers().add(employee);
            return true;
        }
        return false;
    }

}
