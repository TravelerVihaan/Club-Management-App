package com.github.travelervihaan.clubmanagement.controller.employers;

import com.github.travelervihaan.clubmanagement.model.absences.Absence;
import com.github.travelervihaan.clubmanagement.service.absences.AbsenceService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyAbsencesController {

    private AbsenceService absenceService;

    public MyAbsencesController(AbsenceService absenceService){
        this.absenceService = absenceService;
    }

    @GetMapping("/myabsences")
    public String getAbsences(@RequestParam(defaultValue = "all") String filter, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Absence> absences = absenceService.getAbsencesOfEmployee(authentication.getName(), filter);
        model.addAttribute("absences", absences);
        return "myabsences";
    }
}
