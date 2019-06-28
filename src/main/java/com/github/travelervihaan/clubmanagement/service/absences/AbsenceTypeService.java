package com.github.travelervihaan.clubmanagement.service.absences;

import com.github.travelervihaan.clubmanagement.model.absences.AbsenceType;
import com.github.travelervihaan.clubmanagement.repository.absences.AbsenceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;

@Service
public class AbsenceTypeService {

    private AbsenceTypeRepository absenceTypeRepository;

    @Autowired
    public AbsenceTypeService(AbsenceTypeRepository absenceTypeRepository){
        this.absenceTypeRepository = absenceTypeRepository;
    }

    public List<AbsenceType> getAllAbsenceTypes(){
        return absenceTypeRepository.findAll();
    }

    public void deleteAbsenceType(String absenceType){
        if(absenceTypeRepository.findByAbsenceType(absenceType).isPresent())
            absenceTypeRepository.delete(absenceTypeRepository.findByAbsenceType(absenceType).get());
    }

    public void addNewAbsenceType(@Valid AbsenceType absenceType, BindingResult result) {
        if (!result.hasErrors()) {
            if (absenceTypeRepository.findByAbsenceType(absenceType.getAbsenceType()).isEmpty())
                absenceTypeRepository.save(absenceType);
        }
    }
}
