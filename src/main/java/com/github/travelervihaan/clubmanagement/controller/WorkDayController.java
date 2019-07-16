package com.github.travelervihaan.clubmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/workday")
public class WorkDayController {

    @GetMapping
    public String showWorkDayPage(@RequestParam Long workDayId){

        return "workday";
    }
}
