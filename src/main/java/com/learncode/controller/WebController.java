package com.learncode.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learncode.models.Sanpham;
import com.learncode.models.SanphamVaChitiet;
import com.learncode.service.SanphamService;
import com.learncode.service.SanphamVaChitietService;

@Controller
@RequestMapping("/web")
public class WebController {
	
	@Autowired
	SanphamService sanphamService;
	
	@Autowired
	SanphamVaChitietService sanphamvachitietService;
	
	@RequestMapping("/")
	public String home() {
		return "/web/trangchu";
	}
	
	@RequestMapping("/tatcasanpham")
	public String tatcasanpham() {
		return "/web/tatcasanpham";
	}
	@ModelAttribute(name="SANPHAMMOI")
	public List<Sanpham> getSanphammoi() {
		return sanphamService.getSanphammoi();
	}
	
	@GetMapping("/sanpham-chitiet1/{id}")
	public String sanphamchitiet(@PathVariable("id") Long id, ModelMap model) {
//		model.addAttribute("spm", this.sanphamService.findBySanphamAndSanphamchitiet(id).orElse(null));
		model.addAttribute("spm", this.sanphamService.finBySanphamId(id).get());
		model.addAttribute("sizesp", this.sanphamvachitietService.findBySizeSanpham(id));
//		System.out.println(this.sanphamService.findBySanphamAndSanphamchitiet(id).get());
		return "/web/chitiet";
	}
	
	@GetMapping(value="/sp-chitiet/{id}", produces = "application/json")
	@ResponseBody
	public Optional<SanphamVaChitiet> getSizeSanphamVaChitiet(@PathVariable("id") Long id, ModelMap model) {
		return this.sanphamvachitietService.findBySanphamVaChitietId(id);
	}
	
}
