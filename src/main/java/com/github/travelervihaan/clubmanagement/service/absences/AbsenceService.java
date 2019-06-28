package com.github.travelervihaan.clubmanagement.service.absences;

import com.github.travelervihaan.clubmanagement.model.absences.Absence;
import com.github.travelervihaan.clubmanagement.repository.absences.AbsenceApprovalStatusRepository;
import com.github.travelervihaan.clubmanagement.repository.absences.AbsenceRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class AbsenceService {

    private final String WAITING_STATUS = "WAITING";

    private AbsenceRepository absenceRepository;
    private AbsenceApprovalStatusRepository absenceApprovalStatusRepository;

    public AbsenceService(AbsenceRepository absenceRepository,
                          AbsenceApprovalStatusRepository absenceApprovalStatusRepository){
        this.absenceRepository = absenceRepository;
        this.absenceApprovalStatusRepository = absenceApprovalStatusRepository;
    }

    public List<Absence> getAllAbsences(){
        return absenceRepository.findAll();
    }

    public void addNewAbsence(Absence absence, BindingResult result) {
        if (!result.hasErrors()) {
            if (isApprovalStatusExist(WAITING_STATUS))
                absence.setAbsenceApprovalStatus(absenceApprovalStatusRepository.findByStatus(WAITING_STATUS).get());
                absenceRepository.save(absence);
        }
    }

    private boolean isApprovalStatusExist(String status){
        return absenceApprovalStatusRepository.findByStatus(status).isPresent();
    }


}
