package com.github.travelervihaan.clubmanagement.controller.employers;

import com.github.travelervihaan.clubmanagement.model.absences.Absence;
import com.github.travelervihaan.clubmanagement.service.absences.AbsenceTypeService;
import com.github.travelervihaan.clubmanagement.service.absences.NewAbsenceService;
import com.github.travelervihaan.clubmanagement.service.employers.EmployeeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyAbsencesController {

    private NewAbsenceService newAbsenceService;
    private EmployeeService employeeService;
    private AbsenceTypeService absenceTypeService;

    public MyAbsencesController(NewAbsenceService newAbsenceService, EmployeeService employeeService, AbsenceTypeService absenceTypeService){
        this.newAbsenceService = newAbsenceService;
        this.employeeService = employeeService;
        this.absenceTypeService = absenceTypeService;
    }

    @GetMapping("/myabsences")
    public String getAbsences(@RequestParam(defaultValue = "all") String filter, Model model){
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        List<Absence> absences = newAbsenceService
                .getAbsencesOfEmployee(authentication.getName(), filter);
        model.addAttribute("absences", absences);
        model.addAttribute("newAbsence", new Absence());
        model.addAttribute("absenceTypes", absenceTypeService.getAllAbsenceTypes());
        return "myabsences";
    }

    @PostMapping("/new-absence")
    public String sendNewAbsenceRequest(@ModelAttribute Absence absence){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        employeeService
                .getEmployeeByUsername(authentication.getName())
                .ifPresent(absence::setEmployee);
        newAbsenceService.addNewAbsence(absence);
        return "redirect:/myabsences";
    }
}
