package com.github.travelervihaan.clubmanagement.service.payrolls;

import com.github.travelervihaan.clubmanagement.model.employees.Employee;
import com.github.travelervihaan.clubmanagement.model.payrolls.Payroll;
import com.github.travelervihaan.clubmanagement.model.workdiagram.WorkDay;
import com.github.travelervihaan.clubmanagement.repository.payrolls.PayrollRepository;
import com.github.travelervihaan.clubmanagement.service.workdiagram.WorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PayrollService {

    private PayrollRepository payrollRepository;
    private Validator validator;
    private WorkDayService workDayService;

    @Autowired
    public PayrollService(PayrollRepository payrollRepository, WorkDayService workDayService, Validator validator){
        this.payrollRepository = payrollRepository;
        this.validator = validator;
        this.workDayService = workDayService;
    }

    public List<Payroll> getAllPayrolls(String username){
        if(username!=null && !username.equals(""))
            return getAllPayrollsBySearchPattern(username);
        return payrollRepository.findAll();
    }

    public List<Payroll> getAllPayrollsOfEmployee(String username){
        return payrollRepository.findAllByEmployee_Username(username);
    }

    private List<Payroll> getAllPayrollsBySearchPattern(String username){
        return payrollRepository.findAllByEmployee_UsernameOrEmployee_NameOrOrEmployee_Surname(username, username, username);
    }

    public void generatePayrolls(List<Employee> employers) {
        employers.forEach(this::savePayrollOfEmployee);
    }

    private void savePayrollOfEmployee(Employee employee){
        Payroll payroll = new Payroll();
        List<WorkDay> employeeWorkDays = workDayService.getWorkDaysOfEmployeeInCurrentMonth(employee);
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
