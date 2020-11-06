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

}
