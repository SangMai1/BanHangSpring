package com.learncode.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		List<MyItems> listItem = this.reportService.reportReceipt(date, 31);
		
		model.addAttribute("listReceipt", listItem);
		return "thongke";
	}
	
	@GetMapping("/doanhthu")
	public String viewReceiptDoanhThu(HttpSession session, ModelMap model) {
		Date date = new Date();
		List<MyItems> listItem = this.reportService.reportReceiptDoangThu7Ngay(date, 20);
		
		model.addAttribute("listReceipt", listItem);
		return "doanhthuthongke";
	}
	
//	@GetMapping("/doanhthuthang")
//	public String viewReceiptDoanhThuTungThang(HttpSession session, ModelMap model) {
//		Date date = new Date();
//		List<MyItems> listItem = this.reportService.reportReceiptDoanhThuThang(date, 13);
//		System.out.println(listItem.toString());
//		model.addAttribute("listReceipt", listItem);
//		return "/layout/main-layout";
//	}
	
	@GetMapping("/thang")
	public String viewReceiptThang(HttpSession session, ModelMap model) {
		Date date = new Date();
		List<MyItems> listItem = this.reportService.reportReceiptThang(date, 13);
		
		model.addAttribute("listReceipt", listItem);
		return "thongkethang";
	}
	
	//thông kê người đăng kí
	
	@GetMapping("/thongkenguoidangki")
	public String viewReceiptThongKeNguoiDangKiThang(HttpSession session, ModelMap model) {
		Date date = new Date();
		List<MyItems> listItem = this.reportService.reportReceiptThongKeNguoiDangKiThang(date, 13);
		
		model.addAttribute("ThongKeNguoiDangKi", listItem);
		return "ThongKeNguoiDangKi";
	}
	
	@GetMapping("/thongkenguoidangkitungthang")
	public String viewReceiptThongKeNguoiDangKiTungThang(HttpSession session, ModelMap model, @RequestParam("thangdangki") Integer thangdangki, @RequestParam("namdangki") Integer namdangki) {

		List<MyItems> listItem = this.reportService.reportReceiptThongKeNguoiDangKiTungThang(namdangki, thangdangki);
		System.out.println("Aaa"+listItem.toString());
		model.addAttribute("ThongKeNguoiDangKiTungThang", listItem);
		return "ThongKeNguoiDangKiTungThang";
	}
	
	//thống kê số lượng sản phẩm
	@GetMapping("/thongkesoluongsanpham")
	public String viewReceiptThongKeSoLuongSanPhamBanRa(HttpSession session, ModelMap model) {
		Date date = new Date();
		List<MyItems> listItem = this.reportService.reportReceiptThongKeSoLuongSanPhamBanRa(date, 13);
		
		model.addAttribute("ThongKeSoLuongSanPham", listItem);
		return "ThongKeSoLuongSanPham";
	}
	
	@GetMapping("/thongkesoluongsanphamtungthang")
	public String viewReceiptThongKeSoLuongSanPhamTungThang(HttpSession session, ModelMap model, @RequestParam("thangdangki") Integer thangdangki, @RequestParam("namdangki") Integer namdangki) {

		List<MyItems> listItem = this.reportService.reportReceiptThongKeSoLuongSanPhamTungThang(namdangki, thangdangki);
		System.out.println("Aaa"+listItem.toString());
		model.addAttribute("ThongKeSoLuongSanPhamTungThang", listItem);
		return "ThongKeSoLuongSanPhamTungThang";
	}
}
