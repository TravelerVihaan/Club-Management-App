package com.github.travelervihaan.clubmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.travelervihaan.clubmanagement.model.employers.Employee;
import com.github.travelervihaan.clubmanagement.repository.employers.EmployeeRepository;

@Service
public class EmployeeService {

	private EmployeeRepository employeeRepo;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepo, PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Autowired
	public void setEmployeeRepository(EmployeeRepository employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
	
	public void addNewEmployee(Employee employee) {
		// EmployeeRole defaultRole = roleRepository.findByRole(DEFAULT_ROLE);
		String passwordHash = passwordEncoder.encode(employee.getPassword());
		employee.setPassword(passwordHash);
		employeeRepo.save(employee);
	}
}
