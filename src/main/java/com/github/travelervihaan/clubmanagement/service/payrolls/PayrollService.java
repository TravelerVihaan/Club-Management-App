package com.github.travelervihaan.clubmanagement.service.payrolls;

import com.github.travelervihaan.clubmanagement.model.payrolls.Payroll;
import com.github.travelervihaan.clubmanagement.repository.payrolls.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PayrollService {

    private PayrollRepository payrollRepository;

    @Autowired
    public PayrollService(PayrollRepository payrollRepository){
        this.payrollRepository = payrollRepository;
    }

    public List<Payroll> getAllPayrollsOfEmployee(String username){
        return payrollRepository.findAllPayrollsOfEmployee(username);
    }

    //TODO add payroll
}
