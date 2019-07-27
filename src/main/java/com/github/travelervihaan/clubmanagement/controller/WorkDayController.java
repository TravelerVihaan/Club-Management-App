package com.github.travelervihaan.clubmanagement.controller;

import com.github.travelervihaan.clubmanagement.model.workdiagram.WorkDayImportance;
import com.github.travelervihaan.clubmanagement.service.workdiagram.WorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WorkDayController {

    private WorkDayService workDayService;

    @Autowired
    public WorkDayController(WorkDayService workDayService){
        this.workDayService = workDayService;
    }

    @GetMapping("/workday/{workDayId}")
    public String showWorkDayPage(@PathVariable Long workDayId, Model model){
        if(workDayService.getWorkDayById(workDayId).isEmpty())
            return "errors/error404";
        model.addAttribute("workDay",workDayService.getWorkDayById(workDayId).orElseThrow());
        model.addAttribute("importanceLevels", workDayService.getAllImportanceLevels());
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
}
