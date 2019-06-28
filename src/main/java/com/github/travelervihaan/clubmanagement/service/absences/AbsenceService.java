package com.github.travelervihaan.clubmanagement.service.absences;

import com.github.travelervihaan.clubmanagement.model.absences.Absence;
import com.github.travelervihaan.clubmanagement.repository.absences.AbsenceApprovalStatusRepository;
import com.github.travelervihaan.clubmanagement.repository.absences.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;

@Service
public class AbsenceService {

    private final String WAITING_STATUS = "WAITING";
    private final String APPROVED_STATUS = "APPROVED";
    private final String REJECTED_STATUS = "REJECTED";

    private AbsenceRepository absenceRepository;
    private AbsenceApprovalStatusRepository absenceApprovalStatusRepository;

    @Autowired
    public AbsenceService(AbsenceRepository absenceRepository,
                          AbsenceApprovalStatusRepository absenceApprovalStatusRepository){
        this.absenceRepository = absenceRepository;
        this.absenceApprovalStatusRepository = absenceApprovalStatusRepository;
    }

    public List<Absence> getAllAbsences(){
        return absenceRepository.findAll();
    }

    public void addNewAbsence(@Valid Absence absence, BindingResult result) {
        if (!result.hasErrors()) {
            if (isApprovalStatusExist(WAITING_STATUS))
                absence.setAbsenceApprovalStatus(absenceApprovalStatusRepository.findByStatus(WAITING_STATUS).get());
                absenceRepository.save(absence);
        }
    }

    public void changeAbsenceStatus(long absenceId, String newStatus){
        if(newStatus.equals(APPROVED_STATUS) || newStatus.equals(REJECTED_STATUS))
            changeStatus(absenceId, newStatus);
    }

    private boolean isApprovalStatusExist(String status){
        return absenceApprovalStatusRepository.findByStatus(status).isPresent();
    }

    private boolean isAbsenceExist(long absenceId){
        return absenceRepository
                .findById(absenceId)
                .isPresent();
    }

    private void changeStatus(long absenceId, String status) {
        if(isAbsenceExist(absenceId)) {
            Absence absence = absenceRepository
                    .findById(absenceId)
                    .get();
            absence.getAbsenceApprovalStatus().setStatus(status);
            absenceRepository.save(absence);
        }
    }
}
