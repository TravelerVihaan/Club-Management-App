package com.github.travelervihaan.clubmanagement.service.payrolls;

import com.github.travelervihaan.clubmanagement.model.employers.Employee;
import com.github.travelervihaan.clubmanagement.model.payrolls.Payroll;
import com.github.travelervihaan.clubmanagement.model.workdiagram.WorkDay;
import com.github.travelervihaan.clubmanagement.repository.payrolls.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PayrollService {

    private PayrollRepository payrollRepository;
    private Validator validator;

    @Autowired
    public PayrollService(PayrollRepository payrollRepository, Validator validator){
        this.payrollRepository = payrollRepository;
        this.validator = validator;
    }

    public List<Payroll> getAllPayrollsOfEmployee(String username){
        return payrollRepository.findAllByEmployee_Username(username);
    }

    public void generatePayrolls(List<Employee> employers){
        employers.forEach(this::savePayrollOfEmployee);
    }

    private void savePayrollOfEmployee(Employee employee){
        Payroll payroll = new Payroll();
        List<WorkDay> employeeWorkDays = employee.getWorkDays();
        payroll.setWorkedDays(employeeWorkDays.size());
        double workedHours = countWorkedHours(employeeWorkDays);
        payroll.setWorkedHours(workedHours);
        payroll.setMonth(LocalDate.now().minusMonths(1).getMonth().toString()
                +"-"
                +LocalDate.now().minusMonths(1).getYear());
        if(isUZContract(employee))
            payroll.setSalary(calculateSalaryOnUZ(employee, workedHours));
        if(isUOPContract(employee))
            payroll.setSalary(calculateSalaryOnUOP(employee, workedHours));

        payroll.setEmployee(employee);
        savePayrollToDB(payroll);
    }

    private boolean isUOPContract(Employee employee){
        return employee.getEmployeeDetails().getContractType().getContractType().equalsIgnoreCase("UOP");
    }

    private boolean isUZContract(Employee employee){
        return employee.getEmployeeDetails().getContractType().getContractType().equalsIgnoreCase("UZ");
    }

    private void savePayrollToDB(Payroll payroll){
        Set<ConstraintViolation<Payroll>> validationErrors = validator.validate(payroll);
        if(!validationErrors.isEmpty())
            System.err.println("[PAYROLL ERROR] Application generated incorrect payroll for employee: "
                    + payroll.getEmployee().getName() + " " + payroll.getEmployee().getSurname());
        payrollRepository.save(payroll);
    }

    private double countWorkedHours(List<WorkDay> employeeWorkDays){
        double workedHours = 0;
        for(WorkDay workDay: employeeWorkDays)
            workedHours+= workDay.getWorkingTime();
        return workedHours;
    }

    private double calculateSalaryOnUZ(Employee employee, double workedHours){
        return employee.getEmployeeDetails().getSalary()*workedHours;
    }

    private double calculateSalaryOnUOP(Employee employee, double workedHours){
        int workingDays = calculateWorkingDaysOfMonth();
        double salary = employee.getEmployeeDetails().getSalary();
        double daysWhenEmployeeWorks = workedHours/8;
        return (salary/workingDays)*daysWhenEmployeeWorks;
    }

    private int calculateWorkingDaysOfMonth(){
        LocalDate day = LocalDate.now().minusMonths(1);
        int numberOfDays = LocalDate.now().minusMonths(1).lengthOfMonth();
        int workingDays = 0;
        for(int i=0;i<numberOfDays;i++){
            if(isDayIsWorkingDay(day))
                workingDays++;
        }
        return workingDays;
    }

    private boolean isDayIsWorkingDay(LocalDate day){
        return day.getDayOfWeek().getValue()!=6 && day.getDayOfWeek().getValue()!=7;
    }

}
