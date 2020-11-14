package com.learncode.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learncode.comon.Xuly;
import com.learncode.models.Bills;
import com.learncode.repository.BillRepository;

@Service
public class BillImpl implements BillService {

	@Autowired
	BillRepository billRepository;

	@Override
	public void insertBill(Bills bills) {
		this.billRepository.save(bills);
	}
	
	
	@Override
	public void updateBill(Bills bills) {
		billRepository.updateBill(bills.getBill_status(), bills.getBill_id());
	}


	@Override
	public Optional<Bills> findById(Long id) {
		return this.billRepository.findById(id);
	}


	@Override
	public List<Bills> getAllNguoiMuaDaDangKi() {
		return billRepository.getAllNguoiMuaDaDangKi();
	}


	@Override
	public boolean checkLogin(String bill_email, String bill_password) {
		Bills bi = this.billRepository.findByEmail(bill_email);
		if (null != bi && Xuly.checkMd5(bill_password, bi.getBill_password())) {
			return true;
		}
		return false;
	}


	
	
}
