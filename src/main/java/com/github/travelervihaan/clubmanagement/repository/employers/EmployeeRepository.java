package com.github.travelervihaan.clubmanagement.repository.employers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.travelervihaan.clubmanagement.model.employers.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Employee findByUsername(String username);
}
