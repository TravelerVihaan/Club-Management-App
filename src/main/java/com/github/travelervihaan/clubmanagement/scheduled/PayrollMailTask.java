package com.github.travelervihaan.clubmanagement.scheduled;

import com.github.travelervihaan.clubmanagement.model.employers.Employee;
import com.github.travelervihaan.clubmanagement.service.employers.EmployeeService;
import com.github.travelervihaan.clubmanagement.service.mails.PayrollMailService;
import com.github.travelervihaan.clubmanagement.service.payrolls.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PayrollMailTask {

    private PayrollMailService payrollMailService;
    private PayrollService payrollService;
    private EmployeeService employeeService;

    private final String EMPLOYEE_TITLE = "employee";
    private final String MANAGER_TITLE = "manager";

    @Autowired
    public PayrollMailTask(PayrollMailService payrollMailService, PayrollService payrollService, EmployeeService employeeService){
        this.payrollMailService = payrollMailService;
        this.payrollService = payrollService;
        this.employeeService = employeeService;
    }

    @Scheduled(cron = "0 0 10 1 * ?")
    public void preparePayrolls(){
        List<Employee> employers = new ArrayList<>();
        employers.addAll(employeeService.getEmployersByJobTitle(EMPLOYEE_TITLE));
        employers.addAll(employeeService.getEmployersByJobTitle(MANAGER_TITLE));

        payrollService.generatePayrolls(employers);

        payrollMailService.sendPayrollsMails(employers);
    }
}


