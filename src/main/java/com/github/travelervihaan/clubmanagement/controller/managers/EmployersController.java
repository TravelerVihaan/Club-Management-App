package com.github.travelervihaan.clubmanagement.controller.managers;

import com.github.travelervihaan.clubmanagement.model.employers.ContractType;
import com.github.travelervihaan.clubmanagement.model.employers.Employee;
import com.github.travelervihaan.clubmanagement.model.employers.JobTitle;
import com.github.travelervihaan.clubmanagement.service.employers.ContractTypeService;
import com.github.travelervihaan.clubmanagement.service.employers.EmployeeService;
import com.github.travelervihaan.clubmanagement.service.employers.JobTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employers")
public class EmployersController {

    private EmployeeService employeeService;
    private JobTitleService jobTitleService;
    private ContractTypeService contractTypeService;

    @Autowired
    public EmployersController(EmployeeService employeeService, JobTitleService jobTitleService, ContractTypeService contractTypeService){
        this.employeeService = employeeService;
        this.jobTitleService = jobTitleService;
        this.contractTypeService = contractTypeService;
    }

    @GetMapping
    public String getAllEmployers(@RequestParam(required = false) String searchPattern, Model model){
        List<Employee> employers;
        if(searchPattern==null || searchPattern.equals(""))
            employers = employeeService.getAllEmployers();
        else
            employers = employeeService.getEmployersBySearchPattern(searchPattern);
        model.addAttribute("employers",employers);
        return "manager/employers";
    }

    @GetMapping("/{employeeId}")
    public String getEmployee(@PathVariable Long employeeId, Model model){
        model.addAttribute("employee",employeeService.getEmployeeById(employeeId).orElseThrow());
        model.addAttribute("jobTitles", jobTitleService.getAllJobTitles());
        model.addAttribute("contractTypes", contractTypeService.getAllContracts());
        return "manager/employee-page";
    }
}
