package com.github.travelervihaan.clubmanagement.service.employers;

import com.github.travelervihaan.clubmanagement.model.employers.Employee;
import com.github.travelervihaan.clubmanagement.repository.employers.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder){
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Employee> getEmployersByJobTitle(String jobTitle){
        return employeeRepository.findAllByEmployeeDetails_JobTitle_JobTitle(jobTitle);
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepo) {
        this.employeeRepository = employeeRepo;
    }

    public List<Employee> getAllEmployers(){
        return employeeRepository.findAll();
    }

    public void addNewEmployee(Employee employee) {
        // EmployeeRole defaultRole = roleRepository.findByRole(DEFAULT_ROLE);
        String passwordHash = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(passwordHash);
        employeeRepository.save(employee);
    }
}