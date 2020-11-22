package com.learncode.service;

import java.util.Date;
import java.util.List;


import com.learncode.models.MyItems;

public interface ReportService {

	List<MyItems> reportReceipt(Date date, int limit);

	List<MyItems> reportReceiptThang(Date date, int limit);

	List<MyItems> reportReceiptDoangThu7Ngay(Date date, int limit);

	List<MyItems> reportReceiptDoanhThuThang(Date date, int limit);

	List<MyItems> reportReceiptThongKeNguoiDangKiThang(Date date, int limit);

	List<MyItems> reportReceiptThongKeNguoiDangKiTungThang(int year, int month);

	List<MyItems> reportReceiptThongKeSoLuongSanPhamBanRa(Date date, int limit);

	List<MyItems> reportReceiptThongKeSoLuongSanPhamTungThang(int year, int month);




}
