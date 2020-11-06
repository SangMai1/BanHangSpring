package com.learncode.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.learncode.models.LoaiSanPham;
import com.learncode.repository.LoaisanphamRepository;

@Component
@Service
public class LoaisanphamImpl implements LoaisanphamService{
	
	@Autowired
	LoaisanphamRepository loaisanphamRepository;

	@Override
	public void insertLoaisanpham(LoaiSanPham lsp) {
		this.loaisanphamRepository.save(lsp);
	}

	@Override
	public List<LoaiSanPham> findAllLoaisanpham() {
		return loaisanphamRepository.findAllLoaisanpham();
	}

	@Override
	@Cacheable(value = "loaisanpham", key = "#id")
	public Optional<LoaiSanPham> findLoaisanphamById(Long id) {
		return loaisanphamRepository.findLoaisanphamById(id);
	}

	@Override
	@Cacheable(value = "loaisanpham", key = "#lsp.updateby")
	public void updateLoaisanpham(LoaiSanPham lsp) {
		this.loaisanphamRepository.save(lsp);
	}
	
	
}
