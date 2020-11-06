package com.learncode.service;

import java.util.List;
import java.util.Optional;

import com.learncode.models.BillDetail;

public interface BillDetailService {

	void insertBillDetail(BillDetail billDetail);

	List<BillDetail> getSanPhamDatHang();

	Optional<BillDetail> findById(Long id);

	void updateBillDetail(BillDetail billDetail);

	

}
