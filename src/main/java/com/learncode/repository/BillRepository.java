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

import com.learncode.models.Bills;

@Repository
@Transactional
public interface BillRepository extends CrudRepository<Bills, Long> {

	@Modifying
	@Query(value = "INSERT INTO public.ql_bill(bill_id, bill_name, bill_email, bill_phone, bill_address, bill_date, bill_status) VALUES (?, ?, ?, ?, ?, ?, ?);", nativeQuery = true)
	void insertBill(@Param("bill_id") Long bill_id, @Param("bill_name") String bill_name, @Param("bill_email") String bill_email, @Param("bill_phone") String bill_phone, @Param("bill_address") String bill_address, @Param("bill_date") Date bill_date, @Param("bill_status") Integer bill_status);

	@Modifying
	@Query(value = "UPDATE public.ql_bill SET bill_status=? WHERE bill_id=?;", nativeQuery = true)
	void updateBill(@Param("bill_status") Integer bill_status, @Param("bill_id") long bill_id);
	
	@Modifying
	@Query(value = "UPDATE public.ql_bill SET bill_name=?, bill_email=?, bill_phone=?, bill_address=?, bill_password=?, bill_status=? WHERE bill_id=?;", nativeQuery = true)
	void updateBill1(@Param("bill_name") String bill_name, @Param("bill_email") String bill_email, @Param("bill_phone") String bill_phone, @Param("bill_address") String bill_address, @Param("bill_password") String bill_password,  @Param("bill_status") Integer bill_status, @Param("bill_id") long bill_id);
	
	@Query(value = "SELECT * FROM ql_bill WHERE bill_email = ?", nativeQuery = true)
	Bills findByEmail(String bill_emal);
	
	@Query(value = "SELECT * FROM ql_bill WHERE bill_status = 0", nativeQuery = true)
	List<Bills> getAllNguoiMuaDaDangKi();
	
	@Query(value = "SELECT COUNT(*) FROM ql_bill WHERE bill_status = 0", nativeQuery = true)
	long countNguoiDangKi();
	

	
}
