package com.github.travelervihaan.clubmanagement.repository.absences;

import com.github.travelervihaan.clubmanagement.model.absences.AbsenceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceTypeRepository extends JpaRepository<AbsenceType, Long> {
}
