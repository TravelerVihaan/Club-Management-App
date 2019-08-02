package com.github.travelervihaan.clubmanagement.service.employers;

import com.github.travelervihaan.clubmanagement.model.employers.Employee;
import com.github.travelervihaan.clubmanagement.model.workdiagram.WorkDay;
import com.github.travelervihaan.clubmanagement.service.workdiagram.WorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private EmployeeService employeeService;
    private WorkDayService workDayService;

    @Autowired
    public AccountService(EmployeeService employeeService, WorkDayService workDayService){
        this.employeeService = employeeService;
        this.workDayService = workDayService;
    }

    public List<WorkDay> getWorkingDaysInCurrentMonth(String username){
        List<WorkDay> workDays = workDayService.getWorkDaysInCurrentMonth();
        Employee employee = employeeService.getEmployeeByUsername(username).orElseThrow();
        return workDays
                .stream()
                .filter(workDay -> workDay.getEmployers().contains(employee))
                .collect(Collectors.toList());
    }

    public List<WorkDay> getAvailableWorkDaysInCurrentMonth(String username){
        List<WorkDay> workDays = workDayService.getWorkDaysInCurrentMonth();
        Employee employee = employeeService.getEmployeeByUsername(username).orElseThrow();
        return workDays
                .stream()
                .filter(workDay -> !workDay.getEmployers().contains(employee))
                .filter(workDay -> workDay.getEmployers().size()>workDay.getWorkDayImportance().getImportanceLevel())
                .collect(Collectors.toList());
    }

}
