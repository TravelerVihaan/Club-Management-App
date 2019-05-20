package com.github.travelervihaan.clubmanagement.repository;

import com.github.travelervihaan.clubmanagement.model.employers.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmloyeeDetailsRepository extends JpaRepository<EmployeeDetails, Long> {

}
