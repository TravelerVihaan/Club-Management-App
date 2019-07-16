package com.github.travelervihaan.clubmanagement.controller;

import com.github.travelervihaan.clubmanagement.service.workdiagram.WorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        model.addAttribute("workDay",workDayService.getWorkDayById(workDayId));
        return "workday";
    }
}
