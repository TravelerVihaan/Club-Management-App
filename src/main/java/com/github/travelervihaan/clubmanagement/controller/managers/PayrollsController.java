package com.github.travelervihaan.clubmanagement.controller.managers;

import com.github.travelervihaan.clubmanagement.service.payrolls.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PayrollsController {

    private PayrollService payrollService;

    @Autowired
    public PayrollsController(PayrollService payrollService){
        this.payrollService = payrollService;
    }

    @GetMapping("/payrolls")
    public String getAllPayrolls(@RequestParam(required = false)String username, Model model){
        model.addAttribute("payrolls", payrollService.getAllPayrolls(username));
        return "manager/payrolls";
    }

}
