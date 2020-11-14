package com.learncode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.learncode.models.Sanpham;

public interface SanphamService {

	void insertSanpham(Sanpham sp);

	List<Sanpham> getAllSanpham();

	void updateSanpham(Sanpham sp);

	Optional<Sanpham> finBySanphamId(Long id);

	List<Sanpham> getSanphammoi();

	List<Sanpham> getSanphamAndSanphamchitiet();

	List<Sanpham> searchGiatien(float min, float max);

	List<Sanpham> searchSize(String size);

	List<Sanpham> getAllSanphammoi();

	List<Sanpham> getAllSanphamsale();

	List<Sanpham> getAllSanphambanchay();

	List<Sanpham> getAllSanphamnoibat();

}
