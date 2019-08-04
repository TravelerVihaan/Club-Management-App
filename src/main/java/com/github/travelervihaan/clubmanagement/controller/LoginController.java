package com.github.travelervihaan.clubmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String loginPage(@RequestParam(defaultValue = "false") String error, Model model) {
		model.addAttribute("error", error);
		return "login";
	}
}
