package com.github.travelervihaan.clubmanagement.repository.employees;

import com.github.travelervihaan.clubmanagement.model.employees.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Long> {

}
