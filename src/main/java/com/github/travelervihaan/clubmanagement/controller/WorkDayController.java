package com.github.travelervihaan.clubmanagement.controller;

import com.github.travelervihaan.clubmanagement.model.workdiagram.WorkDayImportance;
import com.github.travelervihaan.clubmanagement.service.employers.EmployeeService;
import com.github.travelervihaan.clubmanagement.service.workdiagram.WorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WorkDayController {

    private WorkDayService workDayService;
    private EmployeeService employeeService;

    @Autowired
    public WorkDayController(WorkDayService workDayService,EmployeeService employeeService){
        this.workDayService = workDayService;
        this.employeeService = employeeService;
    }

    @GetMapping("/workday/{workDayId}")
    public String showWorkDayPage(@PathVariable Long workDayId, Model model){
        if(workDayService.getWorkDayById(workDayId).isEmpty())
            return "errors/error404";
        model.addAttribute("workDay",workDayService.getWorkDayById(workDayId).orElseThrow());
        model.addAttribute("importanceLevels", workDayService.getAllImportanceLevels());
        model.addAttribute("employers", employeeService.getAllEmployers());
        model.addAttribute("employersWorking",workDayService.getWorkDayById(workDayId).orElseThrow().getEmployers());
        return "workday";
    }

    @PostMapping("/workday/{workDayId}/set-worktime")
    public String setWorktime(@PathVariable Long workDayId, @RequestParam int workTime){
        workDayService.setWorkTime(workDayId, workTime);
        return "redirect:/workday/"+workDayId;
    }

    @PostMapping("/workday/{workDayId}/set-artist")
    public String setArtist(@PathVariable Long workDayId, @RequestParam String bookedArtist){
        workDayService.setBookedArtist(workDayId, bookedArtist);
        return "redirect:/workday/"+workDayId;
    }

    @PostMapping("/workday/{workDayId}/set-employers-needed")
    public String setEmployersNeeded(@PathVariable Long workDayId, @RequestParam int workDayImportance){
        workDayService.setImportanceLevel(workDayId, workDayImportance);
        return "redirect:/workday/"+workDayId;
    }

    @PostMapping("/workday/{workDayId}/add-employee")
    public String addEmployeeToWorkday(@PathVariable Long workDayId, @RequestParam String employee){
        workDayService.addEmployeeToWorkDay(workDayId, employee);
        return "redirect:/workday/"+workDayId;
    }

    @PostMapping("/workday/{workDayId}/delete-employee")
    public String deleteEmployeeFromWorkday(@PathVariable Long workDayId, @RequestParam String employee){
        workDayService.dropEmployeeFromWorkDay(workDayId, employee);
        return "redirect:/workday/"+workDayId;
    }
}
