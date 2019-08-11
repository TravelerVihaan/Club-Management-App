package com.github.travelervihaan.clubmanagement.controller;

import com.github.travelervihaan.clubmanagement.service.workdiagram.WorkDiagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeDiagramController {

	private WorkDiagramService workDiagramService;

	@Autowired
	public HomeDiagramController(WorkDiagramService workDiagramService){
		this.workDiagramService = workDiagramService;
	}

	@GetMapping("/")
	public String home(@RequestParam(defaultValue = "WEEK") String interval, Model model) {
		model.addAttribute("workDiagram",workDiagramService.getMultipleWorkDays(interval));
		return "index";
	}

}
