package com.learncode.service;

import java.util.List;
import java.util.Optional;

import com.learncode.models.Bills;

public interface BillService {

	void insertBill(Bills bills);

	boolean checkLogin(String bill_email, String bill_password);

	List<Bills> getAllNguoiMuaDaDangKi();

	Optional<Bills> findById(Long id);

	void updateBill(Bills bills);

	void updateBill1(Bills b);

	long countNguoiDangKi();

	List<Bills> searchBillName(String bill_name);


}
