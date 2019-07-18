package com.github.travelervihaan.clubmanagement.controller;

import com.github.travelervihaan.clubmanagement.service.employers.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    private EmployeeService employeeService;

    @Autowired
    public AccountController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/account")
    public String getAccountPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(employeeService.getEmployeeByUsername(authentication.getName()).isEmpty())
            return "error404";
        model.addAttribute("employee", employeeService.getEmployeeByUsername(authentication.getName()));
        return "account";
    }
}
