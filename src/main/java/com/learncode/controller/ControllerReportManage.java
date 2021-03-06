package com.learncode.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/thongkedoanhthutungngay")
	public String viewReceiptThongKeDoanhThuTungNgay() {
		logger.debug("Đây là thống kê doanh thu từng ngày");
		return "ThongKeDoanhThuTungNgayTrongThang";
	}
	
	@GetMapping("/thongkedoanhthutungthang")
	public String viewReceiptThongKeDoanhThuTungNgayTrongThang(HttpSession session, ModelMap model, @RequestParam("thangdoanhthu") Integer thangdoanhthu, @RequestParam("namdoanhthu") Integer namdoanhthu) {
		logger.debug("Đây là thống kê doanh thu từng tháng");
		session.setAttribute("THANGDOANHTHU", thangdoanhthu);
		session.setAttribute("NAMDOANHTHU", namdoanhthu);
		if(thangdoanhthu == null || namdoanhthu == null) {
			return "ThongKeDoanhThuTungNgayTrongThang";
		}
		List<MyItems> listItem = this.reportService.reportReceiptThongKeDoanhThuTungNgayTrongThang(namdoanhthu, thangdoanhthu);
		model.addAttribute("ThongKeDoanhThuTungNgayTrongThang", listItem);
		return "ThongKeDoanhThuTungNgayTrongThang";
	}
	
	//thông kê người đăng kí
	
	@GetMapping("/thongkenguoidangki")
	public String viewReceiptThongKeNguoiDangKiThang(HttpSession session, ModelMap model) {
		logger.debug("Đây là thống kê người đăng kí");
		Date date = new Date();
		List<MyItems> listItem = this.reportService.reportReceiptThongKeNguoiDangKiThang(date, 13);
		
		model.addAttribute("ThongKeNguoiDangKi", listItem);
		return "ThongKeNguoiDangKi";
	}
	
	@GetMapping("/thongkenguoidangkitungngay")
	public String viewReceiptThongKeNguoiDangKiTungNgay() {
		logger.debug("Đây là thống kê người đăng kí từng ngày");
		return "ThongKeNguoiDangKiTungThang";
	}
	
	@GetMapping("/thongkenguoidangkitungthang")
	public String viewReceiptThongKeNguoiDangKiTungThang(HttpSession session, ModelMap model, @RequestParam("thangdangki") Integer thangdangki, @RequestParam("namdangki") Integer namdangki) {
		logger.debug("Đây là thống kê người đăng kí từng tháng");
		session.setAttribute("THANGDANGKI", thangdangki);
		session.setAttribute("NAMDANGKI", namdangki);
		if(thangdangki == null || namdangki == null) {
			return "ThongKeNguoiDangKiTungThang";
		}
		List<MyItems> listItem = this.reportService.reportReceiptThongKeNguoiDangKiTungThang(namdangki, thangdangki);
		model.addAttribute("ThongKeNguoiDangKiTungThang", listItem);
		return "ThongKeNguoiDangKiTungThang";
	}
	

	
	//thống kê số lượng sản phẩm
	@GetMapping("/thongkesoluongsanpham")
	public String viewReceiptThongKeSoLuongSanPhamBanRa(HttpSession session, ModelMap model) {
		logger.debug("Đây là thống kê số lượng sản phẩm");
		Date date = new Date();
		List<MyItems> listItem = this.reportService.reportReceiptThongKeSoLuongSanPhamBanRa(date, 13);
		
		model.addAttribute("ThongKeSoLuongSanPham", listItem);
		return "ThongKeSoLuongSanPham";
	}
	
	@GetMapping("/thongkesoluongsanphamtungngay")
	public String viewReceiptThongKeSoLuongSanPhamTungNgay() {
		logger.debug("Đây là thống kê số lượng sản phẩm từng ngày");
		return "ThongKeSoLuongSanPhamTungThang";
	}
	
	@GetMapping("/thongkesoluongsanphamtungthang")
	public String viewReceiptThongKeSoLuongSanPhamTungThang(HttpSession session, ModelMap model, @RequestParam("thangdangki") Integer thangdangki, @RequestParam("namdangki") Integer namdangki) {
		logger.debug("Đây là thống kê số lượng sản phẩm từng tháng");
		session.setAttribute("THANG", thangdangki);
		session.setAttribute("NAM", namdangki);
		if(thangdangki == null || namdangki == null) {
			return "ThongKeSoLuongSanPhamTungThang";
		}
		List<MyItems> listItem = this.reportService.reportReceiptThongKeSoLuongSanPhamTungThang(namdangki, thangdangki);
		model.addAttribute("ThongKeSoLuongSanPhamTungThang", listItem);
		return "ThongKeSoLuongSanPhamTungThang";
	}
	
	//thống kê số lượng đơn hàng
	@GetMapping("/thongkesoluongdonhang")
	public String viewReceiptThongKeSoLuongSoLuong(HttpSession session, ModelMap model) {
		logger.debug("Đây là thống kê số lượng đơn hàng");
		Date date = new Date();
		List<MyItems> listItem = this.reportService.reportReceiptThongKeSoLuongDonHang(date, 13);
		
		model.addAttribute("ThongKeSoLuongDonHang", listItem);
		return "ThongKeSoLuongDonHang";
	}
	
	@GetMapping("/thongkesoluongdonhangtungngay")
	public String viewReceiptThongKeSoLuongDonHangTungNgay() {
		logger.debug("Đây là thống kê số lượng đơn hàng từng ngày");
		return "ThongKeSoLuongDonHangTungThang";
	}
	
	@GetMapping("/thongkesoluongdonhangtungthang")
	public String viewReceiptThongKeSoLuongDonHangTungThang(HttpSession session, ModelMap model, @RequestParam("thangdonhang") Integer thangdonhang, @RequestParam("namdonhang") Integer namdonhang) {
		logger.debug("Đây là thống kê số lượng đơn hàng từng tháng");
		session.setAttribute("THANGDONHANG", thangdonhang);
		session.setAttribute("NAMDONHANG", namdonhang);
		if(thangdonhang == null || namdonhang == null) {
			return "ThongKeSoLuongDonHangTungThang";
		}
		List<MyItems> listItem = this.reportService.reportReceiptThongKeSoLuongDonHangTungThang(namdonhang, thangdonhang);
		
		model.addAttribute("ThongKeSoLuongDonHangTungThang", listItem);
		return "ThongKeSoLuongDonHangTungThang";
	}
	
	@GetMapping("/sosanh2nam")
	public String viewReceiptSoSanh2Nam() {
		logger.debug("Đây là thống kê so sánh 2 năm");
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
