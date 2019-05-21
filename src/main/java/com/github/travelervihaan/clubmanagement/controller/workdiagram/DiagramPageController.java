package com.github.travelervihaan.clubmanagement.controller.workdiagram;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiagramPageController {

    @GetMapping("/work-diagram")
    public String defaultDiagramPage(Model model){
        return "workDiagramPage";
    }

    @PostMapping("/specified-work-diagram")
    public String specifiedDiagramPage(Model model, @RequestParam String diagramOption){
        return "workDiagramPage";
    }
}
