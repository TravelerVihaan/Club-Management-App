package com.github.travelervihaan.clubmanagement.controller.managers;

import com.github.travelervihaan.clubmanagement.service.absences.AbsenceStatusChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AbsencesController {

    private AbsenceStatusChangeService absenceStatusChangeService;

    private final String STATUS_ACCEPTED = "accepted";
    private final String STATUS_REJECTED = "rejected";

    @Autowired
    public AbsencesController(AbsenceStatusChangeService absenceStatusChangeService){
        this.absenceStatusChangeService = absenceStatusChangeService;
    }

    @GetMapping("/absences")
    public String getAllAbsences(@RequestParam(required = false)String username, Model model){
        model.addAttribute(absenceStatusChangeService.getAllAbsences(username));
        return "manager/absences";
    }

    @PostMapping("/accept-absence")
    public String acceptAbsence(@RequestParam long idAbsence){
        absenceStatusChangeService.changeAbsenceStatus(idAbsence, STATUS_ACCEPTED);
        return "redirect:/absences";
    }

    @PostMapping("reject-absence")
    public String rejectAbsence(@RequestParam long idAbsence){
        absenceStatusChangeService.changeAbsenceStatus(idAbsence, STATUS_REJECTED);
        return "redirect:/absences";
    }
}
