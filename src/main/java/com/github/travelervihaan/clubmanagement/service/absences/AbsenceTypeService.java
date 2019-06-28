package com.github.travelervihaan.clubmanagement.service.absences;

import com.github.travelervihaan.clubmanagement.model.absences.AbsenceType;
import com.github.travelervihaan.clubmanagement.repository.absences.AbsenceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenceTypeService {

    private AbsenceTypeRepository absenceTypeRepository;

    @Autowired
    public AbsenceTypeService(AbsenceTypeRepository absenceTypeRepository){
        this.absenceTypeRepository = absenceTypeRepository;
    }

    List<AbsenceType> getAllAbsenceTypes(){
        return absenceTypeRepository.findAll();
    }

    public void deleteAbsenceType(String jobTitle){
        if(absenceTypeRepository.findByAbsenceType(jobTitle).isPresent())
            absenceTypeRepository.delete(absenceTypeRepository.findByAbsenceType(jobTitle).get());
    }

    public void addNewAbsenceType(AbsenceType absenceType){
        if(absenceTypeRepository.findByAbsenceType(absenceType.getAbsenceType()).isEmpty())
            absenceTypeRepository.save(absenceType);
    }
}
