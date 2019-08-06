package com.github.travelervihaan.clubmanagement.repository.absences;

import com.github.travelervihaan.clubmanagement.model.absences.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {

    List<Absence> findAllByEmployee_Username(String username);

    List<Absence> findAllByAbsenceFromDayIsBeforeAndAbsenceToDayIsAfter(LocalDate date1, LocalDate date2);

    List<Absence> findAllByEmployee_UsernameOrEmployee_NameOrEmployee_Surname(String username, String name, String surname);

    List<Absence> findAllByAbsenceApprovalStatus_StatusNot(String status);
}
