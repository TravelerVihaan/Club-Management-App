package com.github.travelervihaan.clubmanagement.service.absences;

import com.github.travelervihaan.clubmanagement.model.absences.Absence;
import com.github.travelervihaan.clubmanagement.repository.absences.AbsenceApprovalStatusRepository;
import com.github.travelervihaan.clubmanagement.repository.absences.AbsenceRepository;
import com.github.travelervihaan.clubmanagement.service.mails.AbsenceMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class AbsenceService {

    private final String WAITING_STATUS = "WAITING";
    private final String APPROVED_STATUS = "APPROVED";
    private final String REJECTED_STATUS = "REJECTED";

    private AbsenceRepository absenceRepository;
    private AbsenceApprovalStatusRepository absenceApprovalStatusRepository;
    private AbsenceMailService absenceMailService;

    @Autowired
    public AbsenceService(AbsenceRepository absenceRepository,
                          AbsenceApprovalStatusRepository absenceApprovalStatusRepository,
                          AbsenceMailService absenceMailService){
        this.absenceRepository = absenceRepository;
        this.absenceApprovalStatusRepository = absenceApprovalStatusRepository;
        this.absenceMailService = absenceMailService;
    }

    public List<Absence> getAllAbsences(){
        return absenceRepository.findAll();
    }

    public void addNewAbsence(@Valid Absence absence, BindingResult result) {
        if (!result.hasErrors()) {
            if (isApprovalStatusExist(WAITING_STATUS)) {
                absence.setAbsenceApprovalStatus(absenceApprovalStatusRepository.findByStatus(WAITING_STATUS));
                absenceRepository.save(absence);
                absenceMailService.sendMailInformationAboutAbsence(absence.getEmployee());
            }
        }
    }

    public void changeAbsenceStatus(long absenceId, String newStatus){
        if(newStatus.equals(APPROVED_STATUS) || newStatus.equals(REJECTED_STATUS))
            changeStatus(absenceId, newStatus);
    }

    private boolean isApprovalStatusExist(String status){
        return Optional.ofNullable(absenceApprovalStatusRepository.findByStatus(status)).isPresent();
    }

    private void changeStatus(long absenceId, String status) {
            absenceRepository.findById(absenceId).ifPresent(ab -> saveChangedStatusInDB(ab, status));
    }

    private void saveChangedStatusInDB(Absence absence, String status){
        absence.getAbsenceApprovalStatus().setStatus(status);
        absenceRepository.save(absence);
    }
}
