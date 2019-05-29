package com.github.travelervihaan.clubmanagement.service.employers;

import com.github.travelervihaan.clubmanagement.model.employers.Employee;
import com.github.travelervihaan.clubmanagement.repository.employers.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddEmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    AddEmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public void addNewEmployee(Employee newEmployee){
            try{
                employeeRepository.save(newEmployee);
            }catch(Exception e){
                System.err.println("[ERROR] Problem with adding new employee to database");
            }
    }
}
