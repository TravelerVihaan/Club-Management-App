package com.github.travelervihaan.clubmanagement.controller.managers;

import com.github.travelervihaan.clubmanagement.service.absences.AbsenceStatusChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AbsencesController {

    private AbsenceStatusChangeService absenceStatusChangeService;

    private final String STATUS_ACCEPTED = "accepted";
    private final String STATUS_REJECTED = "rejected";
    private final String STATUS_WAITING = "waiting";

    @Autowired
    public AbsencesController(AbsenceStatusChangeService absenceStatusChangeService){
        this.absenceStatusChangeService = absenceStatusChangeService;
    }

    @GetMapping("/absences")
    public String getAllAbsences(@RequestParam(required = false)String username, Model model){
        model
                .addAttribute(
                        "waitingAbsences",
                        absenceStatusChangeService
                                .getAbsencesOfType
                                        (absenceStatusChangeService
                                                .getAllAbsences(username),STATUS_WAITING));
        model
                .addAttribute(
                        "archivalAbsences",
                        absenceStatusChangeService
                                .getAbsencesOfType
                                        (absenceStatusChangeService
                                                .getAllAbsences(username),STATUS_ACCEPTED)
                                .addAll(absenceStatusChangeService
                                        .getAbsencesOfType
                                                (absenceStatusChangeService
                                                        .getAllAbsences(username),STATUS_REJECTED)));
        return "manager/absences";
    }

    @PostMapping("/absences/{absenceId}/accept-absence")
    public String acceptAbsence(@PathVariable long absenceId){
        absenceStatusChangeService.changeAbsenceStatus(absenceId, STATUS_ACCEPTED);
        return "redirect:/absences";
    }

    @PostMapping("/absences/{absenceId}/reject-absence")
    public String rejectAbsence(@PathVariable long absenceId){
        absenceStatusChangeService.changeAbsenceStatus(absenceId, STATUS_REJECTED);
        return "redirect:/absences";
    }
}
