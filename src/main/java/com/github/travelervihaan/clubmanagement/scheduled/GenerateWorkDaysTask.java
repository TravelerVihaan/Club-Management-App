package com.github.travelervihaan.clubmanagement.scheduled;

import com.github.travelervihaan.clubmanagement.model.workdiagram.WorkDay;
import com.github.travelervihaan.clubmanagement.service.workdiagram.WorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class GenerateWorkDaysTask {

    private WorkDayService workDayService;

    @Autowired
    public GenerateWorkDaysTask(WorkDayService workDayService){
        this.workDayService = workDayService;
    }

    @Scheduled(cron = "0 0 8 * * 1")
    public void generateWorkDays() {
        if(!isWorkDayGeneratedAlready())
            saveMissingWorkDaysInDB();
    }

    private boolean isWorkDayGeneratedAlready(){
        return workDayService.getOneWorkDayByDate(LocalDate.now().plusMonths(5)).isPresent();
    }

    private void saveMissingWorkDaysInDB(){
        WorkDay workDay = workDayService
                .getNewestWorkDay()
                .orElseGet(() -> workDayService.getDefaultWorkDay(LocalDate.now()));
        LocalDate date = workDay.getDate().plusDays(2);
        LocalDate endDate = date.plusMonths(3);
        while(!date.isEqual(endDate)){
            workDayService.createDefaultWorkDay(date);
            date = date.plusDays(1);
        }
    }
}
