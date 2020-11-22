package com.learncode.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.learncode.models.BillDetail;
import com.learncode.models.Bills;

public interface BillDetailService {

	void insertBillDetail(BillDetail billDetail);

	List<BillDetail> getSanPhamDatHang();

	Optional<BillDetail> findById(Long id);

	void updateBillDetail(BillDetail billDetail);

	List<BillDetail> getSanPhamDaMuaThanhCong();

	List<BillDetail> getLichSuMuaHang(Bills b);

	long countSanPhamBanRa();

	long countSoLuongDonHang();

	long countTongDoanhThu();

	Integer thongkeNguoiDangKiThang(Integer thang);




	

}
