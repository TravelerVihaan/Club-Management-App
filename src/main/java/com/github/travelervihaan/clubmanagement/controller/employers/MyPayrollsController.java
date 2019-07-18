package com.github.travelervihaan.clubmanagement.controller.employers;

import com.github.travelervihaan.clubmanagement.model.payrolls.Payroll;
import com.github.travelervihaan.clubmanagement.service.payrolls.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MyPayrollsController {

    private PayrollService payrollService;

    @Autowired
    public MyPayrollsController(PayrollService payrollService){
        this.payrollService = payrollService;
    }

    @GetMapping("/mypayrolls")
    public String getPayrolls(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Payroll> payrolls = payrollService.getAllPayrollsOfEmployee(authentication.getName());
        model.addAttribute("payrolls", payrolls);
        return "mypayrolls";
    }
}
