package com.learncode.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learncode.models.BillDetail;
import com.learncode.models.Items;
import com.learncode.models.MyItems;
import com.learncode.repository.BilldetailsRepository;
import com.learncode.service.ReportService;

@Controller
@RequestMapping("/thongke")
public class ControllerReportManage {

	@Autowired
	ReportService reportService;
	
	@Autowired
	BilldetailsRepository billdetailsRepository;
	
	
	@GetMapping("/thongkedoanhthutungngay")
	public String viewReceiptThongKeDoanhThuTungNgay() {
		return "ThongKeDoanhThuTungNgayTrongThang";
	}
	
	@GetMapping("/thongkedoanhthutungthang")
	public String viewReceiptThongKeDoanhThuTungNgayTrongThang(HttpSession session, ModelMap model, @RequestParam("thangdoanhthu") Integer thangdoanhthu, @RequestParam("namdoanhthu") Integer namdoanhthu) {
		session.setAttribute("THANGDOANHTHU", thangdoanhthu);
		session.setAttribute("NAMDOANHTHU", namdoanhthu);
		if(thangdoanhthu == null || namdoanhthu == null) {
			return "ThongKeDoanhThuTungNgayTrongThang";
		}
		List<MyItems> listItem = this.reportService.reportReceiptThongKeDoanhThuTungNgayTrongThang(namdoanhthu, thangdoanhthu);
		System.out.println("Aaa"+listItem.toString());
		model.addAttribute("ThongKeDoanhThuTungNgayTrongThang", listItem);
		return "ThongKeDoanhThuTungNgayTrongThang";
	}
	
	//thông kê người đăng kí
	
	@GetMapping("/thongkenguoidangki")
	public String viewReceiptThongKeNguoiDangKiThang(HttpSession session, ModelMap model) {
		Date date = new Date();
		List<MyItems> listItem = this.reportService.reportReceiptThongKeNguoiDangKiThang(date, 13);
		
		model.addAttribute("ThongKeNguoiDangKi", listItem);
		return "ThongKeNguoiDangKi";
	}
	
	@GetMapping("/thongkenguoidangkitungngay")
	public String viewReceiptThongKeNguoiDangKiTungNgay() {
		return "ThongKeNguoiDangKiTungThang";
	}
	
	@GetMapping("/thongkenguoidangkitungthang")
	public String viewReceiptThongKeNguoiDangKiTungThang(HttpSession session, ModelMap model, @RequestParam("thangdangki") Integer thangdangki, @RequestParam("namdangki") Integer namdangki) {
		session.setAttribute("THANGDANGKI", thangdangki);
		session.setAttribute("NAMDANGKI", namdangki);
		if(thangdangki == null || namdangki == null) {
			return "ThongKeNguoiDangKiTungThang";
		}
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
	
	@GetMapping("/thongkesoluongsanphamtungngay")
	public String viewReceiptThongKeSoLuongSanPhamTungNgay() {
		return "ThongKeSoLuongSanPhamTungThang";
	}
	
	@GetMapping("/thongkesoluongsanphamtungthang")
	public String viewReceiptThongKeSoLuongSanPhamTungThang(HttpSession session, ModelMap model, @RequestParam("thangdangki") Integer thangdangki, @RequestParam("namdangki") Integer namdangki) {
		session.setAttribute("THANG", thangdangki);
		session.setAttribute("NAM", namdangki);
		if(thangdangki == null || namdangki == null) {
			return "ThongKeSoLuongSanPhamTungThang";
		}
		List<MyItems> listItem = this.reportService.reportReceiptThongKeSoLuongSanPhamTungThang(namdangki, thangdangki);
		System.out.println("Aaa"+listItem.toString());
		model.addAttribute("ThongKeSoLuongSanPhamTungThang", listItem);
		return "ThongKeSoLuongSanPhamTungThang";
	}
	
	//thống kê số lượng đơn hàng
	@GetMapping("/thongkesoluongdonhang")
	public String viewReceiptThongKeSoLuongSoLuong(HttpSession session, ModelMap model) {
		Date date = new Date();
		List<MyItems> listItem = this.reportService.reportReceiptThongKeSoLuongDonHang(date, 13);
		
		model.addAttribute("ThongKeSoLuongDonHang", listItem);
		return "ThongKeSoLuongDonHang";
	}
	
	@GetMapping("/thongkesoluongdonhangtungngay")
	public String viewReceiptThongKeSoLuongDonHangTungNgay() {
		return "ThongKeSoLuongDonHangTungThang";
	}
	
	@GetMapping("/thongkesoluongdonhangtungthang")
	public String viewReceiptThongKeSoLuongDonHangTungThang(HttpSession session, ModelMap model, @RequestParam("thangdonhang") Integer thangdonhang, @RequestParam("namdonhang") Integer namdonhang) {
		session.setAttribute("THANGDONHANG", thangdonhang);
		session.setAttribute("NAMDONHANG", namdonhang);
		if(thangdonhang == null || namdonhang == null) {
			return "ThongKeSoLuongDonHangTungThang";
		}
		List<MyItems> listItem = this.reportService.reportReceiptThongKeSoLuongDonHangTungThang(namdonhang, thangdonhang);
		System.out.println("Aaa"+listItem.toString());
		model.addAttribute("ThongKeSoLuongDonHangTungThang", listItem);
		return "ThongKeSoLuongDonHangTungThang";
	}
	
	@GetMapping("/sosanh2nam")
	public String viewReceiptSoSanh2Nam() {
		return "sosanh2nam";
	}
	
	//so sánh 2 năm
	@GetMapping("/sosanh2namtruocsau")
	public String viewSoSanh2NamTruoc(ModelMap model, HttpSession session, @RequestParam("namtruoc") Integer namtruoc, @RequestParam("namsau") Integer namsau) {
		session.setAttribute("NAMTRUOC", namtruoc);
		session.setAttribute("NAMSAU", namsau);
		if(namtruoc == null) {
			return "sosanh2nam";
		}
		List<Items> items1 = this.reportService.reportReceiptSoSanh2Nam(namtruoc,12);
		model.addAttribute("sosanh1", items1);
		
		List<Items> items2 = this.reportService.reportReceiptSoSanh2Nam(namsau,12);
		model.addAttribute("sosanh2", items2);
		return "sosanh2nam";
	}
	
}
