package com.github.travelervihaan.clubmanagement.repository;

import com.github.travelervihaan.clubmanagement.model.employers.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, Long> {

    JobTitle findByJobTitle(String jobTitle);
}
