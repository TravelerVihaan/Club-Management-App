package com.github.travelervihaan.clubmanagement.service.absences;

import com.github.travelervihaan.clubmanagement.model.absences.Absence;
import com.github.travelervihaan.clubmanagement.repository.absences.AbsenceRepository;
import com.github.travelervihaan.clubmanagement.service.employers.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AbsenceStatusChangeService {

    private AbsenceRepository absenceRepository;
    private EmployeeService employeeService;
    private AbsenceApprovalStatusService absenceApprovalStatusService;

    @Autowired
    public AbsenceStatusChangeService(AbsenceRepository absenceRepository,
                                        EmployeeService employeeService,
                                        AbsenceApprovalStatusService absenceApprovalStatusService){
        this.absenceRepository = absenceRepository;
        this.absenceApprovalStatusService = absenceApprovalStatusService;
        this.employeeService = employeeService;
    }

    @Transactional
    public void changeAbsenceStatus(long absenceId, String newStatus){
        Absence absence = absenceRepository.findById(absenceId).orElseThrow();
        if(isAbsenceNotTooLong(absence)) {
            changeStatus(absenceId, newStatus);
            employeeService.changeEmployeeVacationDays(absence);
        }
    }

    public List<Absence> getAllAbsences(){
        return absenceRepository.findAll();
    }

    private boolean isAbsenceNotTooLong(Absence absence){
        return
                ChronoUnit.DAYS.between(absence.getAbsenceFromDay(),absence.getAbsenceToDay())
                        < absence.getEmployee().getEmployeeDetails().getAvailableVacationDays();
    }

    private void changeStatus(long absenceId, String status) {
        absenceRepository.findById(absenceId).ifPresent(ab -> saveChangedStatusInDB(ab, status));
    }

    private void saveChangedStatusInDB(Absence absence, String status){
        absenceApprovalStatusService
                .getAbsenceApprovalStatus(status)
                .ifPresent(approvalStatus -> absence.setAbsenceApprovalStatus(approvalStatus));
        absenceRepository.save(absence);
    }
}
