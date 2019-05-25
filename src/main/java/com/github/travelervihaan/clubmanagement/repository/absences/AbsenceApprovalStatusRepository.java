package com.github.travelervihaan.clubmanagement.repository.absences;

import com.github.travelervihaan.clubmanagement.model.absences.AbsenceApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceApprovalStatusRepository extends JpaRepository<AbsenceApprovalStatus, Long> {
}
