package com.github.travelervihaan.clubmanagement.controller.employers;

import com.github.travelervihaan.clubmanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/employers")
public class EmployersController {

    private EmployeeService employeeService;

    @Autowired
    public EmployersController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public String employersPage(Model model){
        model.addAttribute("employers", employeeService.getAllEmployers());
        return "admin/employers";
    }


}
