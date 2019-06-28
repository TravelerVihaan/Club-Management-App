package com.github.travelervihaan.clubmanagement.repository.absences;

import com.github.travelervihaan.clubmanagement.model.absences.AbsenceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AbsenceTypeRepository extends JpaRepository<AbsenceType, Long> {

    Optional<AbsenceType> findByAbsenceType(String absenceType);
}
