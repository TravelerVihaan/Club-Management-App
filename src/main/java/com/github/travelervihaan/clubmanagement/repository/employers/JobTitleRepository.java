package com.github.travelervihaan.clubmanagement.repository.employers;

import com.github.travelervihaan.clubmanagement.model.employers.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, Long> {

    Optional<JobTitle> findByJobTitle(String jobTitle);
}
