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
	public int insertSanphamVaChitiet(SanphamVaChitiet spvct) {
		return this.sanphamVaChitietRepository.insertSanphamVaChitiet(spvct.getId(), spvct.getIdsanpham(), spvct.getKichthuoc(), spvct.getSoluong(), spvct.getGiatien(), spvct.getIsdelete());
	}

	
	@Override
	public int updateSanphamVaChitiet(String kichthuoc, Integer soluong, Float giatien, Integer isdelete, Long id) {
		return this.sanphamVaChitietRepository.updateSanphamVaChitiet(kichthuoc, soluong, giatien, isdelete, id);
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
		return sanphamVaChitietRepository.findBySizeSanpham(idsanpham);
	}
	
	
}
