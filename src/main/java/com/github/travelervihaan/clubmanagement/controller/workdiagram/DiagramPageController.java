package com.github.travelervihaan.clubmanagement.controller.workdiagram;

import com.github.travelervihaan.clubmanagement.service.workdiagram.WorkDiagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiagramPageController {

    private WorkDiagramService workDiagramService;

    @Autowired
    public DiagramPageController(WorkDiagramService workDiagramService){
        this.workDiagramService = workDiagramService;
    }

    @GetMapping("/work-diagram")
    public String defaultDiagramPage(Model model){
        model.addAttribute("workDaysList", workDiagramService.generateDefaultWorkDaysList());
        return "workDiagramPage";
    }

    @PostMapping("/specified-work-diagram")
    public String specifiedDiagramPage(Model model, @RequestParam String diagramOption){
        return "workDiagramPage";
    }
}
