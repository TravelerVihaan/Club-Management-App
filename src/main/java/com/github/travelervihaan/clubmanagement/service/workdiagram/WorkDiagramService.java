package com.github.travelervihaan.clubmanagement.service.workdiagram;

import com.github.travelervihaan.clubmanagement.model.workdiagram.WorkDay;
import com.github.travelervihaan.clubmanagement.repository.workdiagram.WorkDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkDiagramService {

    private WorkDayRepository workDayRepository;
    private LocalDateTime localDateTime;

    @Autowired
    public WorkDiagramService(WorkDayRepository workDayRepository){
        this.workDayRepository = workDayRepository;
    }

    public List<WorkDay> generateDefaultWorkDaysList(){
        try {
            List<WorkDay> workDays = workDayRepository.
                    findAllByDayOfMonthAndMonthAndYearOrderByDayOfMonthAsc(localDateTime.getDayOfMonth(),
                            localDateTime.getMonthValue(),
                            localDateTime.getYear());
        }catch(Exception e){
            System.err.println("[ERROR] Problem with database connection (generateDefaultWorkDayList)");
        }
        return null;
    }
}
