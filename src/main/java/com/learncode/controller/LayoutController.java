package com.learncode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/layout")
public class LayoutController {

	@RequestMapping("/")
	public String home() {
		return "/layout/main-layout";
	}
}
