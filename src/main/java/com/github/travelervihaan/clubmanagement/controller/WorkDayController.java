package com.github.travelervihaan.clubmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WorkDayController {

    @GetMapping("/workday/{workDayId}")
    public String showWorkDayPage(@PathVariable Long workDayId, Model model){

        return "workday";
    }
}
