package com.github.travelervihaan.clubmanagement.repository.employers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.travelervihaan.clubmanagement.model.employers.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Optional<Employee> findByUsername(String username);

	List<Employee> findAllByEmployeeDetails_JobTitle_JobTitle(String jobTitle);

	List<Employee> findAllByEmployeeDetails_DayOfHireTerminateBefore(LocalDate date);

}
