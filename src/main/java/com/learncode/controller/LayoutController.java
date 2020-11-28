package com.learncode.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learncode.models.MyItems;
import com.learncode.service.ReportService;

@Controller
@RequestMapping("/layout")
public class LayoutController {

	@Autowired
	ReportService reportService;
	
	@RequestMapping("/")
	public String home(ModelMap model) {
		Date date = new Date();
		List<MyItems> listItem = this.reportService.reportReceiptDoanhThuThang(date, 13);
	
		model.addAttribute("listReceipt", listItem);
		return "/layout/main-layout";
	}
}
