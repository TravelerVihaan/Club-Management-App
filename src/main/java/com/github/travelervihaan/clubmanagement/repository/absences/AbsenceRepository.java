package com.github.travelervihaan.clubmanagement.repository.absences;

import com.github.travelervihaan.clubmanagement.model.absences.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {

}
