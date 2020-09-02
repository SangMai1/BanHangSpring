package com.learncode.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learncode.models.LoaiSanPham;
import com.learncode.repository.LoaisanphamRepository;

@Service
public class LoaisanphamImpl implements LoaisanphamService{
	
	@Autowired
	LoaisanphamRepository loaisanphamRepository;

	@Override
	public void insertLoaisanpham(LoaiSanPham lsp) {
		this.loaisanphamRepository.insertLoaisanpham(lsp.getId(), lsp.getMaloaisanpham(), lsp.getTenloaisanpham(), lsp.getCreateday(), lsp.getCreateby(), lsp.getUpdateday(), lsp.getUpdateby(), lsp.getIsdelete());
	}

	@Override
	public List<LoaiSanPham> findAllLoaisanpham() {
		return loaisanphamRepository.findAllLoaisanpham();
	}

	@Override
	public Optional<LoaiSanPham> findLoaisanphamById(Long id) {
		return loaisanphamRepository.findLoaisanphamById(id);
	}

	@Override
	public void updateLoaisanpham(LoaiSanPham lsp) {
		loaisanphamRepository.updateLoaisanpham(lsp.getMaloaisanpham(), lsp.getTenloaisanpham(), lsp.getUpdateday(), lsp.getUpdateby(), lsp.getIsdelete(), lsp.getId());
	}
	
	
}
