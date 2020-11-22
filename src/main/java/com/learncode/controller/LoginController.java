package com.learncode.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.learncode.models.Bills;
import com.learncode.models.MyItems;
import com.learncode.service.BillDetailService;
import com.learncode.service.BillService;
import com.learncode.service.ReportService;

@Controller
public class LoginController {

	@Autowired
	ReportService reportService;
	
	@Autowired
	BillService billService;
	
	@Autowired
	BillDetailService billDetailService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(ModelMap model) {
		return "Login";
	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String successPage(ModelMap model) {
		Date date = new Date();
		List<MyItems> listItem = this.reportService.reportReceiptDoanhThuThang(date, 13);
		System.out.println(listItem.toString());
		model.addAttribute("listReceipt", listItem);
		model.addAttribute("countNguoiDangKi", this.billService.countNguoiDangKi());
		model.addAttribute("countSanPhamBanRa", this.billDetailService.countSanPhamBanRa());
		model.addAttribute("countSoLuongDonHang", this.billDetailService.countSoLuongDonHang());
		model.addAttribute("countTongDoanhThu", this.billDetailService.countTongDoanhThu());
		return "/layout/main-layout";
	}

	@GetMapping("/403")
	public String error403() {
		return "403";
	}
}
