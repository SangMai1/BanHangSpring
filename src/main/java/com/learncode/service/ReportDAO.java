package com.learncode.service;

import java.util.Date;
import java.util.List;

import com.learncode.models.Items;
import com.learncode.models.MyItems;

public interface ReportDAO {

	List<MyItems> reportReceipt(Date date, int limit);

	List<MyItems> reportReceiptThang(Date date, int limit);

	List<MyItems> reportReceiptDoanhThuThang(Date date, int limit);

	List<MyItems> reportReceiptThongKeNguoiDangKiThang(Date date, int limit);

	List<MyItems> reportReceiptThongKeNguoiDangKiTungThang(int year, int month);

	List<MyItems> reportReceiptThongKeSoLuongSanPhamBanRa(Date date, int limit);

	List<MyItems> reportReceiptThongKeSoLuongSanPhamTungThang(int year, int month);

	List<Items> reportReceiptSoSanh2Nam(int nam, int thang);


	List<MyItems> reportReceiptThongKeSoLuongDonHangTungThang(int year, int month);

	List<MyItems> reportReceiptThongKeDoanhThuTungNgayTrongThang(int year, int month);

	List<MyItems> reportReceiptThongKeSoLuongDonHang(Date date, int limit);




}
