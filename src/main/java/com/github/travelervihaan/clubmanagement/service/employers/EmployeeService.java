package com.github.travelervihaan.clubmanagement.service.employers;

import com.github.travelervihaan.clubmanagement.model.absences.Absence;
import com.github.travelervihaan.clubmanagement.model.employers.Employee;
import com.github.travelervihaan.clubmanagement.repository.employers.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

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

    public Optional<Employee> getEmployeeByUsername(String username){
        return employeeRepository.findByUsername(username);
    }

    public void changeEmployeeVacationDays(Absence absence){
        Employee employee = absence.getEmployee();
        Long vacationDays = ChronoUnit
                .DAYS
                .between(absence.getAbsenceFromDay(),absence.getAbsenceToDay());
        employee.getEmployeeDetails().setAvailableVacationDays(
                employee.getEmployeeDetails().getAvailableVacationDays()-vacationDays.intValue());
        employeeRepository.save(employee);
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
