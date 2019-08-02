package com.github.travelervihaan.clubmanagement.service.workdiagram;

import com.github.travelervihaan.clubmanagement.model.workdiagram.WorkDayImportance;
import com.github.travelervihaan.clubmanagement.repository.workdiagram.WorkDayImportanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkDayImportanceService {

    private WorkDayImportanceRepository workDayImportanceRepository;

    @Autowired
    public WorkDayImportanceService(WorkDayImportanceRepository workDayImportanceRepository){
        this.workDayImportanceRepository = workDayImportanceRepository;
    }

    WorkDayImportance getDefaultImportanceLevel(){
        return workDayImportanceRepository.findByImportanceLevel(2);
    }

    public List<WorkDayImportance> getAllWorkDayImportanceLevels(){
        return workDayImportanceRepository.findAll();
    }

    public Optional<WorkDayImportance> getWorkDayImportanceByLevel(int level){
        return Optional.ofNullable(workDayImportanceRepository.findByImportanceLevel(level));
    }

    public void addNewImportanceLevel(WorkDayImportance workDayImportance){
        if(!isImportanceLevelExist(workDayImportance.getImportanceLevel()))
            workDayImportanceRepository.save(workDayImportance);
    }

    private boolean isImportanceLevelExist(int importanceLevel){
        return Optional.ofNullable(workDayImportanceRepository.findByImportanceLevel(importanceLevel)).isPresent();
    }
}
