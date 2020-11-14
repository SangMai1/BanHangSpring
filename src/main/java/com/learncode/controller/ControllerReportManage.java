package com.learncode.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learncode.models.MyItems;
import com.learncode.service.ReportService;

@Controller
@RequestMapping("/thongke")
public class ControllerReportManage {

	@Autowired
	ReportService reportService;
	
	@GetMapping("/")
	public String viewReceipt(HttpSession session, ModelMap model) {
		Date date = new Date();
		List<MyItems> listItem = this.reportService.reportReceipt(date, 10);
		System.out.println(listItem.toString());
		model.addAttribute("listReceipt", listItem);
		return "thongke";
	}
}
