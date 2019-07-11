package com.github.travelervihaan.clubmanagement.service.workdiagram;

import com.github.travelervihaan.clubmanagement.model.workdiagram.WorkDay;
import com.github.travelervihaan.clubmanagement.repository.workdiagram.WorkDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class WorkDayService {

    private WorkDayRepository workDayRepository;
    private WorkDayImportanceService workDayImportanceService;

    @Autowired
    public WorkDayService(WorkDayRepository workDayRepository){
        this.workDayRepository = workDayRepository;
    }

    public Optional<WorkDay> getOneWorkDayByDate(LocalDate date){
        return workDayRepository.findByDate(date);
    }

    public Optional<WorkDay> getNewestWorkDay(){
        return Optional.ofNullable(workDayRepository.findFirstByOrderByDateAsc());
    }

    public void createDefaultWorkDay(LocalDate date){
        WorkDay workDay = new WorkDay();
        workDay.setDate(date);
        workDay.setWorkDayImportance(workDayImportanceService.getDefaultImportanceLevel());
        workDay.setWorkingTime(8);
        workDayRepository.save(workDay);
    }

    public WorkDay createFirstWorkDay(){
        WorkDay workDay = new WorkDay();
        workDay.setDate(LocalDate.now());
        workDay.setWorkDayImportance(workDayImportanceService.getDefaultImportanceLevel());
        workDay.setWorkingTime(8);
        workDayRepository.save(workDay);
        return workDay;
    }

}
