package com.learncode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.learncode.models.LoaiSanPham;

public interface LoaisanphamService {

	void insertLoaisanpham(LoaiSanPham lsp);

	List<LoaiSanPham> findAllLoaisanpham();

	Optional<LoaiSanPham> findLoaisanphamById(Long id);

	void updateLoaisanpham(LoaiSanPham lsp);

	void deleteLoaisanpham(LoaiSanPham lsp);

	List<LoaiSanPham> searchTenLoaiSanPham(String tenloaisanpham);

}
