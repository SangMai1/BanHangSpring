package com.learncode.service;

import java.util.List;
import java.util.Optional;

import com.learncode.models.Sanpham;

public interface SanphamService {

	void insertSanpham(Sanpham sp);

	List<Sanpham> getAllSanpham();

	int updateSanpham(Sanpham sp);

	Optional<Sanpham> finBySanphamId(Long id);

}
