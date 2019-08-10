package com.github.travelervihaan.clubmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/login")
	public String loginPage(@RequestParam(defaultValue = "false") String error, Model model) {
		model.addAttribute("error", error);
		System.out.println(bCryptPasswordEncoder.encode("dupa1234"));
		return "login";
	}
}
