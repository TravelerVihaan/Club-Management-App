package com.github.travelervihaan.clubmanagement.service.workdiagram;

import com.github.travelervihaan.clubmanagement.model.workdiagram.WorkDay;
import com.github.travelervihaan.clubmanagement.repository.workdiagram.WorkDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WorkDiagramService {

    private WorkDayRepository workDayRepository;

    private final String WEEK = "WEEK";
    private final String MONTH = "MONTH";
    private final String QUARTER = "QUARTER";

    @Autowired
    public WorkDiagramService(WorkDayRepository workDayRepository){
        this.workDayRepository = workDayRepository;
    }

    public List<WorkDay> getMultipleWorkDays(String interval){
        if(interval.equals(WEEK)) return getWeekWorkDays();
        if(interval.equals(MONTH)) return getMonthWorkDays();
        if(interval.equals(QUARTER)) return getQuarterWorkDays();
        return getWeekWorkDays();
    }

    private List<WorkDay> getWeekWorkDays(){
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = startDate.plusDays(8);
        return workDayRepository.findByDateBetweenOrderByDateAsc(startDate, endDate);
    }

    private List<WorkDay> getMonthWorkDays(){
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = startDate.plusWeeks(5);
        return workDayRepository.findByDateBetweenOrderByDateAsc(startDate, endDate);
    }

    private List<WorkDay> getQuarterWorkDays(){
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = startDate.plusMonths(3);
        return workDayRepository.findByDateBetweenOrderByDateAsc(startDate, endDate);
    }
}
