package com.github.travelervihaan.clubmanagement.service.absences;

import com.github.travelervihaan.clubmanagement.model.absences.Absence;
import com.github.travelervihaan.clubmanagement.repository.absences.AbsenceRepository;
import com.github.travelervihaan.clubmanagement.service.mails.AbsenceMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeAbsenceService {

    private final String WAITING_STATUS = "waiting";

    private AbsenceRepository absenceRepository;
    private AbsenceApprovalStatusService absenceApprovalStatusService;
    private AbsenceMailService absenceMailService;
    private Validator validator;

    @Autowired
    public EmployeeAbsenceService(AbsenceRepository absenceRepository,
                          AbsenceApprovalStatusService absenceApprovalStatusService,
                          AbsenceMailService absenceMailService,
                          Validator validator){
        this.absenceRepository = absenceRepository;
        this.absenceApprovalStatusService = absenceApprovalStatusService;
        this.validator = validator;
        this.absenceMailService = absenceMailService;
    }

    public void addNewAbsence(Absence absence){
        if(isAbsenceRequestValid(absence)) {
            absence.setAbsenceApprovalStatus
                    (absenceApprovalStatusService
                            .getAbsenceApprovalStatus(WAITING_STATUS)
                            .orElseThrow());
            Set<ConstraintViolation<Absence>> validationErrors = validator.validate(absence);
            if (validationErrors.isEmpty()) {
                absenceRepository.save(absence);
                absenceMailService.sendMailInformationAboutAbsence(absence.getEmployee());
            }
        }
    }

    private boolean isAbsenceRequestValid(Absence absence){
        if(!isDatesCorrect(absence.getAbsenceFromDay(),absence.getAbsenceToDay()))
            return false;
        if(isSickLeave(absence))
            return true;
        return (isAbsenceTooLong(absence));
    }


    private boolean isSickLeave(Absence absence){
        return absence.getAbsenceType().getAbsenceType().equalsIgnoreCase("Sick leave");
    }

    private boolean isDatesCorrect(LocalDate startDate, LocalDate endDate){
        return startDate.isBefore(endDate)
                && startDate.isAfter(LocalDate.now())
                && endDate.isAfter(LocalDate.now());
        }

        private boolean isAbsenceTooLong(Absence absence){
            return ChronoUnit.DAYS.between(absence.getAbsenceFromDay(),absence.getAbsenceToDay())
                    > absence.getEmployee().getEmployeeDetails().getAvailableVacationDays();
        }

    public List<Absence> getAbsencesOfEmployee(String username, String filter){
        List<Absence> absences = absenceRepository.findAllByEmployee_Username(username);
        if(!filter.equalsIgnoreCase("all")){
            absences = absences.stream()
                    .filter( absence -> absence.getAbsenceApprovalStatus()
                            .getStatus()
                            .equalsIgnoreCase(filter))
                    .collect(Collectors.toList());
        }
        return absences;
    }


}
