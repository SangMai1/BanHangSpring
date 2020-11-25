package com.learncode.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learncode.models.Items;
import com.learncode.models.MyItems;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportDAO reportDAO;
	
	@Override
	public List<MyItems> reportReceipt(Date date, int limit){
		return reportDAO.reportReceipt(date, limit);
	}

	@Override
	public List<MyItems> reportReceiptThang(Date date, int limit) {
		return reportDAO.reportReceiptThang(date, limit);
	}


	@Override
	public List<MyItems> reportReceiptDoanhThuThang(Date date, int limit) {
		return reportDAO.reportReceiptDoanhThuThang(date, limit);
	}

	@Override
	public List<MyItems> reportReceiptThongKeNguoiDangKiThang(Date date, int limit) {
		return reportDAO.reportReceiptThongKeNguoiDangKiThang(date, limit);
	}

	@Override
	public List<MyItems> reportReceiptThongKeNguoiDangKiTungThang(int year, int month) {
		return reportDAO.reportReceiptThongKeNguoiDangKiTungThang(year, month);
	}

	@Override
	public List<MyItems> reportReceiptThongKeSoLuongSanPhamBanRa(Date date, int limit) {
		return reportDAO.reportReceiptThongKeSoLuongSanPhamBanRa(date, limit);
	}

	@Override
	public List<MyItems> reportReceiptThongKeSoLuongSanPhamTungThang(int year, int month) {
		return reportDAO.reportReceiptThongKeSoLuongSanPhamTungThang(year, month);
	}

	@Override
	public List<Items> reportReceiptSoSanh2Nam(int nam, int thang) {
		return reportDAO.reportReceiptSoSanh2Nam(nam, thang);
	}


	@Override
	public List<MyItems> reportReceiptThongKeSoLuongDonHangTungThang(int year, int month) {
		return reportDAO.reportReceiptThongKeSoLuongDonHangTungThang(year, month);
	}

	@Override
	public List<MyItems> reportReceiptThongKeDoanhThuTungNgayTrongThang(int year, int month) {
		return reportDAO.reportReceiptThongKeDoanhThuTungNgayTrongThang(year, month);
	}

	@Override
	public List<MyItems> reportReceiptThongKeSoLuongDonHang(Date date, int limit) {
		return reportDAO.reportReceiptThongKeSoLuongDonHang(date, limit);
	}

	

	
	
}
