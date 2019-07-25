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
        if(interval.equals(WEEK))
            return getWeekWorkDays();

        if(interval.equals(MONTH))
            return getMonthWorkDays();

        if(interval.equals(QUARTER))
            return getQuarterWorkDays();

        return getWeekWorkDays();
    }

    private List<WorkDay> getWeekWorkDays(){
        LocalDate startDate = LocalDate.now().minusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(6);
        return workDayRepository.findByDateBetweenOrderByDateAsc(startDate, endDate);
    }

    private List<WorkDay> getMonthWorkDays(){
        LocalDate startDate = LocalDate.now().minusWeeks(2);
        LocalDate endDate = LocalDate.now().plusWeeks(3);
        return workDayRepository.findByDateBetweenOrderByDateAsc(startDate, endDate);
    }

    private List<WorkDay> getQuarterWorkDays(){
        LocalDate startDate = LocalDate.now().minusMonths(1);
        LocalDate endDate = LocalDate.now().plusMonths(2);
        return workDayRepository.findByDateBetweenOrderByDateAsc(startDate, endDate);
    }
}
