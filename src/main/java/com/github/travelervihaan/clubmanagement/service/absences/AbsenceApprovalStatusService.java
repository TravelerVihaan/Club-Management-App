package com.github.travelervihaan.clubmanagement.service.absences;

import com.github.travelervihaan.clubmanagement.model.absences.AbsenceApprovalStatus;
import com.github.travelervihaan.clubmanagement.repository.absences.AbsenceApprovalStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenceApprovalStatusService {

    private AbsenceApprovalStatusRepository absenceApprovalStatusRepository;

    @Autowired
    public AbsenceApprovalStatusService(AbsenceApprovalStatusRepository absenceApprovalStatusRepository){
        this.absenceApprovalStatusRepository = absenceApprovalStatusRepository;
    }

    List<AbsenceApprovalStatus> getAllAbsenceApprovalStatuses(){
        return absenceApprovalStatusRepository.findAll();
    }

    public void deleteAbsenceApprovalStatus(String status){
        if(absenceApprovalStatusRepository.findByStatus(status).isPresent())
            absenceApprovalStatusRepository.delete(absenceApprovalStatusRepository.findByStatus(status).get());
    }

    public void addNewAbsenceApprovalStatus(AbsenceApprovalStatus absenceApprovalStatus){
        if(absenceApprovalStatusRepository.findByStatus(absenceApprovalStatus.getStatus()).isEmpty())
            absenceApprovalStatusRepository.save(absenceApprovalStatus);
    }
}
