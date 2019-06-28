package com.github.travelervihaan.clubmanagement.repository.absences;

import com.github.travelervihaan.clubmanagement.model.absences.AbsenceApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AbsenceApprovalStatusRepository extends JpaRepository<AbsenceApprovalStatus, Long> {

    Optional<AbsenceApprovalStatus> findByStatus(String status);
}
