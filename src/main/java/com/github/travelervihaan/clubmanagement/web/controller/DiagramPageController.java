package com.github.travelervihaan.clubmanagement.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiagramPageController {

    @GetMapping
    public String defaultDiagramPage(Model model){
        return "workDiagramPage";
    }
}
