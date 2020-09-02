package com.learncode.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learncode.comon.Xuly;
import com.learncode.models.ChucNang1;
import com.learncode.models.Nguoidung;
import com.learncode.service.ChucNang1Service;
import com.learncode.service.NguoiDungService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	NguoiDungService nguoiDungService;
	
	@Autowired
	ChucNang1Service chuNang1Service;
	@RequestMapping("/")
	public String show(ModelMap model, HttpSession session) {
		if (session.getAttribute("USERNAME") != null) {
			model.addAttribute("LOGIN");
			return "/layout/main-layout";
		}
		return "Login";
	}
	
	@PostMapping("/checkLogin")
	public String checkLogin(@RequestParam("tennguoidung") String tennguoidung, @RequestParam("password") String password, ModelMap model, HttpSession session) {
		if (nguoiDungService.checkLogin(tennguoidung, password)) {
			
			System.out.println("Login success");
			session.setAttribute("USERNAME", tennguoidung);
			List<ChucNang1> ls = this.chuNang1Service.findChucnangByTennguoidung(tennguoidung);
			session.setAttribute("MENU", ls);
			model.addAttribute("LOGIN");
			return "/layout/main-layout";
		} else {
			System.out.println("Login false");
			model.addAttribute("ERROR", "tennguoidung va password not exist");
		}
		return "Login";
	}
	
	@GetMapping(value="/check", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String check(String url, ModelMap model, HttpSession session) {
		String tennguoidung = (String) session.getAttribute("USERNAME");
		Nguoidung nd = this.nguoiDungService.findUrlChucNang(tennguoidung);
		if (Xuly.checkUrl(nd, url)) {
			return "1";
		}
		return "-1";
	}
}
