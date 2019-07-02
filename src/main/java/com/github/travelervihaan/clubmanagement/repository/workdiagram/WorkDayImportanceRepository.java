package com.github.travelervihaan.clubmanagement.repository.workdiagram;

import com.github.travelervihaan.clubmanagement.model.workdiagram.WorkDayImportance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkDayImportanceRepository extends JpaRepository<WorkDayImportance,Long> {

    WorkDayImportance findByImportanceLevel(int importance);
}
