package com.learncode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learncode.models.SanphamVaChitiet;
import com.learncode.repository.SanphamVaChitietRepository;

@Service
public class SanphamVaChitietImpl implements SanphamVaChitietService{
	
	@Autowired
	SanphamVaChitietRepository sanphamVaChitietRepository;

	@Override
	public void insertSanphamVaChitiet(SanphamVaChitiet spvct) {
		this.sanphamVaChitietRepository.save(spvct);
		//this.sanphamVaChitietRepository.insertSanphamVaChitiet(spvct.getId(), spvct.getSanphams().getId(), spvct.getKichthuoc(), spvct.getSoluong(), spvct.getGiatien(), spvct.getGiamgia(), spvct.getIsdelete());
	}

	
	@Override
	public void updateSanphamVaChitiet(SanphamVaChitiet spvct) {
		this.sanphamVaChitietRepository.updateSanphamVaChitiet(spvct.getKichthuoc(), spvct.getSoluong(), spvct.getGiatien(), spvct.getGiamgia(), spvct.getIsdelete(), spvct.getId());
	}


	@Override
	public Optional<SanphamVaChitiet> findBySanphamVaChitietId(Long id) {
		return this.sanphamVaChitietRepository.findBySanphamVaChitietId(id);
	}


	@Override
	public List<SanphamVaChitiet> getAll() {
		return this.sanphamVaChitietRepository.getAll();
	}


	@Override
	public List<SanphamVaChitiet> findBySizeSanpham(Long idsanpham) {
		return this.sanphamVaChitietRepository.findBySizeSanpham(idsanpham);
	}


	@Override
	public Optional<SanphamVaChitiet> findBySanphamId(Long id) {
		return this.sanphamVaChitietRepository.findBySanphamId(id);
	}


	@Override
	public List<SanphamVaChitiet> searchGiatien(float min, float max) {
		return sanphamVaChitietRepository.searchGiatien(min, max);
	}
	
	
	
}
