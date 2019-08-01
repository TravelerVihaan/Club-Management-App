package com.github.travelervihaan.clubmanagement.controller.employers;

import com.github.travelervihaan.clubmanagement.service.employers.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class AccountController {

    private EmployeeService employeeService;

    @Autowired
    public AccountController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getAccountPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(employeeService.getEmployeeByUsername(authentication.getName()).isEmpty())
            return "errors/error404";
        model.addAttribute("employee", employeeService.getEmployeeByUsername(authentication.getName()).orElseThrow());
        return "myaccount";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String oldPassword, @RequestParam String newPassword1, @RequestParam String newPassword2, Model model){
        return "redirect:/account";
    }

    @PostMapping("/change-email")
    public String changeEmail(){
        return "redirect:/account";
    }

    @PostMapping("/add-to-workday")
    public String addToWorkday(){
        return "redirect:/account";
    }
}