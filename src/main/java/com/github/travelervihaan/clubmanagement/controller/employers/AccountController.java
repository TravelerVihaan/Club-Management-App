package com.github.travelervihaan.clubmanagement.controller.employers;

import com.github.travelervihaan.clubmanagement.service.employers.AccountService;
import com.github.travelervihaan.clubmanagement.service.employers.EmployeeService;
import com.github.travelervihaan.clubmanagement.service.workdiagram.WorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {

    private EmployeeService employeeService;
    private AccountService accountService;

    @Autowired
    public AccountController(EmployeeService employeeService, AccountService accountService){
        this.employeeService = employeeService;
        this.accountService = accountService;
    }

    @GetMapping
    public String getAccountPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(employeeService.getEmployeeByUsername(authentication.getName()).isEmpty())
            return "errors/error404";
        model.addAttribute("employee", employeeService.getEmployeeByUsername(authentication.getName()).orElseThrow());
        model.addAttribute("workingDays", accountService.getWorkingDaysInCurrentMonth(authentication.getName()));
        model.addAttribute("availableDays",accountService.getAvailableWorkDaysInCurrentMonth(authentication.getName()));
        return "myaccount";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String oldPassword, @RequestParam String newPassword1, @RequestParam String newPassword2, Model model){
        return "redirect:/account";
    }

    @PostMapping("/change-email")
    public String changeEmail(@RequestParam String oldEmail, @RequestParam String newEmail1, @RequestParam String newEmail2, Model model){
        return "redirect:/account";
    }

    @PostMapping("/add-to-workday/{workDayId}")
    public String addToWorkday(@PathVariable Long workDayId){
        return "redirect:/account";
    }
}
