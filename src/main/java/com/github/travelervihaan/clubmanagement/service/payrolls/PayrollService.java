package com.github.travelervihaan.clubmanagement.service.payrolls;

import com.github.travelervihaan.clubmanagement.model.employers.Employee;
import com.github.travelervihaan.clubmanagement.model.payrolls.Payroll;
import com.github.travelervihaan.clubmanagement.model.workdiagram.WorkDay;
import com.github.travelervihaan.clubmanagement.repository.payrolls.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        List<Employee> employersUOP = employers.stream().filter(this::isUOPContract).collect(Collectors.toList());
        generatePayrollsForUOP(employersUOP);
        List<Employee> employersUZ = employers.stream().filter(this::isUZContract).collect(Collectors.toList());
        generatePayrollsForUZ(employersUZ);
    }

    private boolean isUOPContract(Employee employee){
        return employee.getEmployeeDetails().getContractType().getContractType().equalsIgnoreCase("UOP");
    }

    private boolean isUZContract(Employee employee){
        return employee.getEmployeeDetails().getContractType().getContractType().equalsIgnoreCase("UZ");
    }

    private void generatePayrollsForUOP(List<Employee> employers){

    }

    private void generatePayrollsForUZ(List<Employee> employers){
        employers.forEach(this::savePayrollOfUZEmployee);
    }

    private void savePayrollOfUZEmployee(Employee employee){
        Payroll payroll = new Payroll();
        List<WorkDay> employeeWorkDays = employee.getWorkDays();
        payroll.setWorkedDays(employeeWorkDays.size());
        double workedHours = countWorkedHours(employeeWorkDays);
        payroll.setWorkedHours(workedHours);
        payroll.setSalary(calculateSalaryOnUZ(employee, workedHours));
        payroll.setEmployee(employee);
        Set<ConstraintViolation<Payroll>> validationErrors = validator.validate(payroll);
        if(!validationErrors.isEmpty())
            System.err.println("[PAYROLL ERROR] Application generated incorrect payroll for employee: "
                    + employee.getName() + " " + employee.getSurname());
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

}
