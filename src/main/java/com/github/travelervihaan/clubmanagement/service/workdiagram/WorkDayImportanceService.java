package com.github.travelervihaan.clubmanagement.service.workdiagram;

import com.github.travelervihaan.clubmanagement.model.workdiagram.WorkDayImportance;
import com.github.travelervihaan.clubmanagement.repository.workdiagram.WorkDayImportanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class WorkDayImportanceService {

    private WorkDayImportanceRepository workDayImportanceRepository;

    @Autowired
    public WorkDayImportanceService(WorkDayImportanceRepository workDayImportanceRepository){
        this.workDayImportanceRepository = workDayImportanceRepository;
    }

    public List<WorkDayImportance> getAllWorkDayImportanceLevels(){
        return workDayImportanceRepository.findAll();
    }

    public void addNewImportanceLevel(@Valid WorkDayImportance workDayImportance, BindingResult result){
        if(!result.hasErrors())
            if(!isImportanceLevelExist(workDayImportance.getImportanceLevel()))
                workDayImportanceRepository.save(workDayImportance);
    }

    private boolean isImportanceLevelExist(int importanceLevel){
        return Optional.ofNullable(workDayImportanceRepository.findByImportanceLevel(importanceLevel)).isPresent();
    }
}
