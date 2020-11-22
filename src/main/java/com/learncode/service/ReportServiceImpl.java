package com.learncode.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<MyItems> reportReceiptDoangThu7Ngay(Date date, int limit) {
		return reportDAO.reportReceiptDoangThu7Ngay(date, limit);
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

	

	
	
}
