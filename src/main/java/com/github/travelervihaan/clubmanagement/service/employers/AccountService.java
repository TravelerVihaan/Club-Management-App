package com.github.travelervihaan.clubmanagement.service.employers;

import com.github.travelervihaan.clubmanagement.model.employers.Employee;
import com.github.travelervihaan.clubmanagement.model.workdiagram.WorkDay;
import com.github.travelervihaan.clubmanagement.service.workdiagram.WorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private EmployeeService employeeService;
    private WorkDayService workDayService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final String WRONG_PASSWORD_MESSAGE = "You provided wrong password";
    private final String PASSWORDS_NOT_MATCH = "Your new passwords don't match";
    private final String PASSWORD_CHANGE_SUCCESS = "Your password is changed";

    @Autowired
    public AccountService(EmployeeService employeeService, WorkDayService workDayService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.employeeService = employeeService;
        this.workDayService = workDayService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String changeUserPassword(String username, String oldPassword, String newPassword1, String newPassword2){
        Employee employee = employeeService.getEmployeeByUsername(username).orElseThrow();
        if(!employee.getPassword().equals(bCryptPasswordEncoder.encode(oldPassword)))
            return WRONG_PASSWORD_MESSAGE;
        if(!newPassword1.equals(newPassword2))
            return PASSWORDS_NOT_MATCH;
        employee.setPassword(bCryptPasswordEncoder.encode(newPassword1));
        employeeService.saveUpdatedEmployee(employee);
        return PASSWORD_CHANGE_SUCCESS;

    }

    public String changeUserEmail(){

    }

    public void addEmployeeToWorkDay(Long workDayId, String username){
        workDayService.addEmployeeToWorkDay(workDayId, username);
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
