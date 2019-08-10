package com.github.travelervihaan.clubmanagement.service.employers;

import com.github.travelervihaan.clubmanagement.dto.EmployeeDto;
import com.github.travelervihaan.clubmanagement.model.employers.Employee;
import com.github.travelervihaan.clubmanagement.repository.employers.EmployeeRepository;
import com.github.travelervihaan.clubmanagement.service.mappers.EmployeeDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AddEmployeeService {

    private EmployeeRepository employeeRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AddEmployeeService(EmployeeRepository employeeRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.employeeRepository = employeeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void addNewEmployee(Employee newEmployee){
        newEmployee.getEmployeeDetails().setHireDay(LocalDate.now());
    }
}
