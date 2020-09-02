package com.learncode.service;

import java.util.List;
import java.util.Optional;

import com.learncode.models.LoaiSanPham;

public interface LoaisanphamService {

	void insertLoaisanpham(LoaiSanPham lsp);

	List<LoaiSanPham> findAllLoaisanpham();

	Optional<LoaiSanPham> findLoaisanphamById(Long id);

	void updateLoaisanpham(LoaiSanPham lsp);

}
