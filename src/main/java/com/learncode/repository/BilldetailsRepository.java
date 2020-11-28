package com.learncode.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learncode.models.BillDetail;
import com.learncode.models.Bills;
import com.learncode.models.SanphamVaChitiet;

@Repository
@Transactional
public interface BilldetailsRepository extends CrudRepository<BillDetail, Long> {

	@Modifying
	@Query(value = "INSERT INTO public.ql_billdetails(billdetail_id, bills, sanphamvachitiet, billdetail_quantity, billdetail_price, billdetail_sale, billdetail_status, billdetail_date) VALUES (?, ?, ?, ?, ?, ?, ?);", nativeQuery = true)
	void insertBillDetail(@Param("billdetail_id") Long billdetail_id, @Param("bills") Bills bills, @Param("sanphamvachitiet") SanphamVaChitiet sanphamvachitiet, @Param("billdetail_quantity") Integer billdetail_quantity, @Param("billdetail_price") Float billdetail_price, @Param("billdetail_sale") Integer billdetail_sale, @Param("billdetail_status") Boolean billdetail_status, @Param("billdetail_date") Date billdetail_date);

	@Modifying
	@Query(value = "UPDATE public.ql_billdetails SET billdetail_status=? WHERE billdetail_id=?;", nativeQuery = true)
	void updateBillDetail(@Param("billdetail_status") Integer billdetail_status, @Param("billdetail_id") long billdetail_id);
	
	@Query(value = "SELECT * FROM ql_billdetails", nativeQuery = true)
	List<BillDetail> getSanPhamDatHang();
	
	@Query(value = "SELECT * FROM ql_billdetails WHERE billdetail_status = 2", nativeQuery = true)
	List<BillDetail> getSanPhamDaMuaThanhCong();
	
	@Query(value = "SELECT * FROM ql_billdetails bd\r\n" + 
			"LEFT JOIN ql_bill b ON b.bill_id = bd.bills\r\n" + 
			"WHERE b.bill_email = ?", nativeQuery = true)
	List<BillDetail> getLichSuMuaHang(@Param("bill_email") String bill_email);
	
	
	//thống kê số lượng đơn hàng từng tháng
	@Query(value = "SELECT count(*) soluong\r\n" + 
			"FROM ql_billdetails \r\n" + 
			"WHERE date_part('month', billdetail_date) = ?", nativeQuery = true)
	int thongkeThang(@Param("thang") Integer thang);
	
	//thống kê doanh thu năm
	@Query(value = "SELECT sum(billdetail_price) tien\r\n" + 
			"			FROM ql_billdetails \r\n" + 
			"			WHERE date_part('month', billdetail_date) = ?", nativeQuery = true)
	Integer doanhThuThongkeThang(@Param("thang") Integer thang);
	
	@Query(value = "SELECT sum(billdetail_price) tien \r\n" + 
			"						FROM ql_billdetails \r\n" + 
			"						WHERE billdetail_date = ?", nativeQuery = true)
	Integer thongkeDoanhThuTungNgayTrongThang(@Param("thang") Date thang);
	
	
	@Query(value = "SELECT SUM(billdetail_quantity) FROM ql_billdetails", nativeQuery = true)
	long countSanPhamBanRa();
	
	@Query(value = "SELECT COUNT(*) FROM ql_billdetails", nativeQuery = true)
	long countSoLuongDonHang();
	
	@Query(value = "SELECT SUM(billdetail_price) FROM ql_billdetails", nativeQuery = true)
	long countTongDoanhThu();
	
	//thống kê người đăng kí mới
	
	@Query(value = "SELECT count(*) \r\n" + 
			"FROM ql_bill\r\n" + 
			"WHERE date_part('month', bill_date) = ? AND bill_status = 0", nativeQuery = true)
	Integer thongkeNguoiDangKiThang(@Param("thang") Integer thang);
	
	@Query(value = "SELECT count(*) \r\n" + 
			"			FROM ql_bill \r\n" + 
			"			WHERE bill_date = ? AND bill_status = 0", nativeQuery = true)
	Integer thongkeNguoiDangKiTungNgayTrongThang(@Param("thang") Date thang);
	
	
	//thống kê số lượng sản phẩm
	
	@Query(value = "SELECT sum(billdetail_quantity)  \r\n" + 
			"			FROM ql_billdetails \r\n" + 
			"			WHERE date_part('month', billdetail_date) = ?", nativeQuery = true)
	Integer thongKeSoLuongSanPham(@Param("thang") Integer thang);
	
	@Query(value = "SELECT sum(billdetail_quantity)  \r\n" + 
			"						FROM ql_billdetails \r\n" + 
			"						WHERE billdetail_date = ?", nativeQuery = true)
	Integer thongkeSoLuongSanPhamTrongThang(@Param("thang") Date thang);
	
	//thống kê số lượng đơn hàng
	@Query(value = "SELECT count(*) \r\n" + 
			"	FROM ql_billdetails \r\n" + 
			"	WHERE date_part('month', billdetail_date) = ?", nativeQuery = true)
	Integer thongKeSoLuongDonHang(@Param("thang") Integer thang);
	
	@Query(value = "SELECT count(*) \r\n" + 
			"	FROM ql_billdetails \r\n" + 
			"	WHERE billdetail_date = ?", nativeQuery = true)
	Integer thongkeSoLuongDonHangTrongThang(@Param("thang") Date thang);
	
	// so sánh 2 năm
	@Query(value = "SELECT max(sp.tensanpham) FROM ql_billdetails bd \r\n" + 
			"			LEFT JOIN qtht_sanphamvachitiet spct ON spct.id = bd.sanphamvachitiet \r\n" + 
			"			LEFT JOIN qtht_sanpham sp ON sp.id = spct.idsanpham\r\n" + 
			"			WHERE date_part('year', bd.billdetail_date) = ? AND date_part('month', bd.billdetail_date) = ?\r\n" + 
			"			GROUP BY sp.tensanpham\r\n" + 
			"			ORDER BY sum(bd.billdetail_quantity) DESC limit 1", nativeQuery = true)
	String sosanh2NamSanPham(@Param("nam") Integer nam, @Param("thang") Integer thang);
	
	@Query(value = "SELECT sum(bd.billdetail_quantity) billdetail_quantity  FROM ql_billdetails bd \r\n" + 
			"			LEFT JOIN qtht_sanphamvachitiet spct ON spct.id = bd.sanphamvachitiet \r\n" + 
			"			LEFT JOIN qtht_sanpham sp ON sp.id = spct.idsanpham\r\n" + 
			"			WHERE date_part('year', bd.billdetail_date) = ? AND date_part('month', bd.billdetail_date) = ?\r\n" + 
			"			GROUP BY sp.tensanpham\r\n" + 
			"			ORDER BY sum(bd.billdetail_quantity) DESC limit 1", nativeQuery = true)
	String sosanh2NamSoLuong(@Param("nam") Integer nam, @Param("thang") Integer thang);
	
	@Query(value = "SELECT * FROM ql_billdetails WHERE billdetail_status = ?", nativeQuery = true)
	List<BillDetail> listSearchTrangThai(@Param("billdetail_status") int billdetail_status);
}
