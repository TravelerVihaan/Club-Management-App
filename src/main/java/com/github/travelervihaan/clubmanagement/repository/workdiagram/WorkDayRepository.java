package com.github.travelervihaan.clubmanagement.repository.workdiagram;


import com.github.travelervihaan.clubmanagement.model.workdiagram.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {
}
