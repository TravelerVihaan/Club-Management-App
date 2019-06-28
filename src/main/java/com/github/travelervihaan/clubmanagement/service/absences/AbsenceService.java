package com.github.travelervihaan.clubmanagement.service.absences;

import com.github.travelervihaan.clubmanagement.repository.absences.AbsenceRepository;
import org.springframework.stereotype.Service;

@Service
public class AbsenceService {

    private AbsenceRepository absenceRepository;

    public AbsenceService(AbsenceRepository absenceRepository){
        this.absenceRepository = absenceRepository;
    }
}
