package com.learncode.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learncode.models.BillDetail;
import com.learncode.models.Bills;
import com.learncode.repository.BilldetailsRepository;

@Service
public class BillDetailImpl implements BillDetailService {

	@Autowired
	BilldetailsRepository billdetailsRepository;

	
	@Override
	public List<BillDetail> getSanPhamDatHang() {
		return this.billdetailsRepository.getSanPhamDatHang();
	}


	@Override
	public Optional<BillDetail> findById(Long id) {
		return this.billdetailsRepository.findById(id);
	}


	@Override
	public void insertBillDetail(BillDetail billDetail) {
//		billdetailsRepository.insertBillDetail(billDetail.getBilldetail_id(), billDetail.getBills(), billDetail.getSanphamvachitiet(), billDetail.getBilldetail_quantity(), billDetail.getBilldetail_price(), billDetail.getBilldetail_sale(), billDetail.isBilldetail_status());
		this.billdetailsRepository.save(billDetail);
	}


	@Override
	public void updateBillDetail(BillDetail billDetail) {
		billdetailsRepository.updateBillDetail(billDetail.getBilldetail_status(), billDetail.getBilldetail_id());
	}


	@Override
	public List<BillDetail> getSanPhamDaMuaThanhCong() {
		return billdetailsRepository.getSanPhamDaMuaThanhCong();
	}


	
	@Override
	public List<BillDetail> getLichSuMuaHang(Bills b) {
		return this.billdetailsRepository.getLichSuMuaHang(b.getBill_email());
	}


	
	
	
}
