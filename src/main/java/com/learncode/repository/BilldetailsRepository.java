package com.learncode.repository;

import java.util.List;

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
	@Query(value = "INSERT INTO public.ql_billdetails(billdetail_id, bills, sanphamvachitiet, billdetail_quantity, billdetail_price, billdetail_sale, billdetail_status) VALUES (?, ?, ?, ?, ?, ?, ?);", nativeQuery = true)
	void insertBillDetail(@Param("billdetail_id") Long billdetail_id, @Param("bills") Bills bills, @Param("sanphamvachitiet") SanphamVaChitiet sanphamvachitiet, @Param("billdetail_quantity") Integer billdetail_quantity, @Param("billdetail_price") Float billdetail_price, @Param("billdetail_sale") Integer billdetail_sale, @Param("billdetail_status") Boolean billdetail_status);

	@Modifying
	@Query(value = "UPDATE public.ql_billdetails SET billdetail_status=? WHERE billdetail_id=?;", nativeQuery = true)
	void updateBillDetail(@Param("billdetail_status") Integer billdetail_status, @Param("billdetail_id") long billdetail_id);
	
	@Query(value = "SELECT * FROM ql_billdetails", nativeQuery = true)
	List<BillDetail> getSanPhamDatHang();
}
