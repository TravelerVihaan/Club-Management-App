package com.github.travelervihaan.clubmanagement.repository.employers;

import com.github.travelervihaan.clubmanagement.model.employers.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Long> {

}
