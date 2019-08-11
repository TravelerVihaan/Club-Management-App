package com.github.travelervihaan.clubmanagement.scheduled;

import com.github.travelervihaan.clubmanagement.model.employers.Employee;
import com.github.travelervihaan.clubmanagement.service.employers.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VacationDaysTask {

    private EmployeeService employeeService;

    @Autowired
    public VacationDaysTask(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @Scheduled(cron = "0 8 1 1 * ?")
    public void addVacationDays(){
        List<Employee> employers = employeeService.getAllEmployers();
        employers.forEach(this::saveEmployeeWithVacationDays);
    }

    private void saveEmployeeWithVacationDays(Employee employee){
        employee.getEmployeeDetails()
                .setAvailableVacationDays(employee.getEmployeeDetails()
                        .getTotalVacationDays());
        employeeService.saveUpdatedEmployee(employee);
    }
}
