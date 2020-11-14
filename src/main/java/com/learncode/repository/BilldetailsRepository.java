package com.learncode.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beust.jcommander.Parameter;
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
			"WHERE b.bill_email = ? AND bd.billdetail_status = 2", nativeQuery = true)
	List<BillDetail> getLichSuMuaHang(@Param("bill_email") String bill_email);
	
	@Query(value = "SELECT count(*) FROM ql_billdetails WHERE billdetail_date=?", nativeQuery = true)
	int thongke(@Param("billdetail_date") Date billdetail_date);
	
}
