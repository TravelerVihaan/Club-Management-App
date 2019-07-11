package com.github.travelervihaan.clubmanagement.repository.workdiagram;


import com.github.travelervihaan.clubmanagement.model.workdiagram.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {

    Optional<WorkDay> findByDate(LocalDate date);

    List<WorkDay> findByDateBetweenOrderByDateAsc(LocalDate startDate, LocalDate endDate);

    WorkDay findFirstByOrderByDateAsc();
}
