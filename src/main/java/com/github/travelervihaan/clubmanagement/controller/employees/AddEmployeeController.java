package com.github.travelervihaan.clubmanagement.controller.employees;

import com.github.travelervihaan.clubmanagement.model.employees.Employee;
import com.github.travelervihaan.clubmanagement.service.employees.AddEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/add-employee")
public class AddEmployeeController {

    private AddEmployeeService addEmployeeService;

    @Autowired
    public AddEmployeeController(AddEmployeeService addEmployeeService){
        this.addEmployeeService = addEmployeeService;
    }

    @GetMapping(value = {"","/"})
    public String addNewEmployeeForm(Model model){
        model.addAttribute("newEmployee", new Employee());
        return "addEmployee";
    }

    @PostMapping("/save")
    public String saveEmployeeFromForm(@ModelAttribute Employee newEmployee, Model model){
        addEmployeeService.addNewEmployee(newEmployee);
        model.addAttribute("successMessage", "SUCCESS");
        return "addEmployee";
    }

}
