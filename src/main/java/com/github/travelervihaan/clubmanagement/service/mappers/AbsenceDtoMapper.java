package com.github.travelervihaan.clubmanagement.service.mappers;

import com.github.travelervihaan.clubmanagement.dto.AbsenceDto;
import com.github.travelervihaan.clubmanagement.model.absences.Absence;
import com.github.travelervihaan.clubmanagement.service.absences.AbsenceTypeService;
import com.github.travelervihaan.clubmanagement.service.employers.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbsenceDtoMapper {

    private AbsenceTypeService absenceTypeService;
    private EmployeeService employeeService;

    @Autowired
    public AbsenceDtoMapper(AbsenceTypeService absenceTypeService, EmployeeService employeeService){
        this.absenceTypeService = absenceTypeService;
        this.employeeService = employeeService;
    }

    public Absence convertFromDtoToEntity(AbsenceDto absenceDto){
        Absence absence = new Absence();
        absence.setAbsenceFromDay(absenceDto.getAbsenceFromDay());
        absence.setAbsenceToDay(absenceDto.getAbsenceToDay());
        absence.setAbsenceType(absenceTypeService.getAbsenceTypeByType(absenceDto.getAbsenceType()).orElseThrow());
        absence.setEmployee(employeeService.getEmployeeByUsername(absenceDto.getUsername()).orElseThrow());
        return absence;
    }
}
