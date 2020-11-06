package com.learncode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(ModelMap model) {
		return "Login";
	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String successPage() {
		return "/layout/main-layout";
	}

	@GetMapping("/403")
	public String error403() {
		return "403";
	}
}
