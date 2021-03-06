package com.github.travelervihaan.clubmanagement.service.absences;

import com.github.travelervihaan.clubmanagement.model.absences.AbsenceApprovalStatus;
import com.github.travelervihaan.clubmanagement.repository.absences.AbsenceApprovalStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class AbsenceApprovalStatusService {

    private AbsenceApprovalStatusRepository absenceApprovalStatusRepository;

    @Autowired
    public AbsenceApprovalStatusService(AbsenceApprovalStatusRepository absenceApprovalStatusRepository){
        this.absenceApprovalStatusRepository = absenceApprovalStatusRepository;
    }

    public Optional<AbsenceApprovalStatus> getAbsenceApprovalStatus(String status){
        return Optional.ofNullable(absenceApprovalStatusRepository.findByStatus(status));
    }

    public List<AbsenceApprovalStatus> getAllAbsenceApprovalStatuses(){
        return absenceApprovalStatusRepository.findAll();
    }

    public void deleteAbsenceApprovalStatus(String status){
        if(isAbsenceApprovalStatusExist(status))
            absenceApprovalStatusRepository.delete(absenceApprovalStatusRepository.findByStatus(status));
    }

    public void addNewAbsenceApprovalStatus(@Valid AbsenceApprovalStatus absenceApprovalStatus, BindingResult result) {
        if (!result.hasErrors()) {
            if (!isAbsenceApprovalStatusExist(absenceApprovalStatus.getStatus()))
                absenceApprovalStatusRepository.save(absenceApprovalStatus);
        }
    }

    private boolean isAbsenceApprovalStatusExist(String status){
        return Optional.ofNullable(absenceApprovalStatusRepository.findByStatus(status)).isPresent();
    }
}
