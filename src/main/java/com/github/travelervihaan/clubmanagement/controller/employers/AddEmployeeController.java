package com.github.travelervihaan.clubmanagement.controller.employers;

import com.github.travelervihaan.clubmanagement.service.employers.AddEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddEmployeeController {

    private AddEmployeeService addEmployeeService;

    @Autowired
    public AddEmployeeController(AddEmployeeService addEmployeeService){
        this.addEmployeeService = addEmployeeService;
    }

    @GetMapping("/add-employee")
    public String addNewEmployee(){
        return "addEmployee";
    }
}
