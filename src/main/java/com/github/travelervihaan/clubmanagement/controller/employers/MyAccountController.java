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

@Controller
@RequestMapping("/myaccount")
public class MyAccountController {

    private EmployeeService employeeService;

    @Autowired
    public MyAccountController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getMyAccountPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("employee", employeeService
                .getEmployeeByUsername(authentication.getName())
                .orElseThrow());
        return "myaccount";
    }

    @PostMapping("/change-password")
    public String changePassword(){
        return "redirect:/myaccount";
    }

    @PostMapping("/change-email")
    public String changeEmail(){
        return "redirect:/myaccount";
    }

    @PostMapping("/add-to-workday")
    public String addToWorkday(){
        return "redirect:/myaccount";
    }
}
