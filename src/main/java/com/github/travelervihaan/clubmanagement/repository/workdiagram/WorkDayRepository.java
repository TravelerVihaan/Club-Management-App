package com.github.travelervihaan.clubmanagement.repository.workdiagram;


import com.github.travelervihaan.clubmanagement.model.workdiagram.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {

    List<WorkDay> findAllByDayOfMonthAndMonthAndYearOrderByDayOfMonthAsc(int dayOfMonth, int month, int year);
}
