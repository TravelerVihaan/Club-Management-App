package com.github.travelervihaan.clubmanagement.service.payrolls;

import com.github.travelervihaan.clubmanagement.model.employers.Employee;
import com.github.travelervihaan.clubmanagement.model.payrolls.Payroll;
import com.github.travelervihaan.clubmanagement.repository.payrolls.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PayrollService {

    private PayrollRepository payrollRepository;

    @Autowired
    public PayrollService(PayrollRepository payrollRepository){
        this.payrollRepository = payrollRepository;
    }

    public List<Payroll> getAllPayrollsOfEmployee(String username){
        return payrollRepository.findAllByEmployee_Username(username);
    }

    public void generatePayrolls(List<Employee> employers){
        List<Employee> employersUOP = employers.stream().filter(this::isUOPContract).collect(Collectors.toList());
        List<Employee> employersUZ = employers.stream().filter(this::isUZContract).collect(Collectors.toList());

    }

    private boolean isUOPContract(Employee employee){
        return employee.getEmployeeDetails().getContractType().getContractType().equalsIgnoreCase("UOP");
    }

    private boolean isUZContract(Employee employee){
        return employee.getEmployeeDetails().getContractType().getContractType().equalsIgnoreCase("UZ");
    }


}
