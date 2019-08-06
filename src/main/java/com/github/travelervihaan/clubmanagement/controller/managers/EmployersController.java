package com.github.travelervihaan.clubmanagement.controller.managers;

import com.github.travelervihaan.clubmanagement.model.employers.Employee;
import com.github.travelervihaan.clubmanagement.service.employers.ContractTypeService;
import com.github.travelervihaan.clubmanagement.service.employers.EmployeeService;
import com.github.travelervihaan.clubmanagement.service.employers.JobTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping()
    public String changeEmployeeSalary(@PathVariable Long employeeId, @RequestParam double newSalary){
        employeeService.changeEmployeeSalary(employeeId,newSalary);
        return "redirect:/employers/"+employeeId;
    }

    @PostMapping()
    public String changeEmployeeContract(@PathVariable Long employeeId, @RequestParam String newContract){
        employeeService.changeEmployeeContract(employeeId, contractTypeService.getContractTypeByType(newContract));
        return "redirect:/employers/"+employeeId;
    }

    @PostMapping()
    public String changeEmployeeJobPosition(@PathVariable Long employeeId, @RequestParam String newPosition){
        employeeService.changeEmployeeJobPosition(employeeId,jobTitleService.getJobTitleByTitle(newPosition));
        return "redirect:/employers/"+employeeId;
    }
}
