package com.learncode.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learncode.models.Sanpham;
import com.learncode.repository.SanphamRepository;

@Service
public class SanphamImpl implements SanphamService{
	
	@Autowired
	SanphamRepository sanphamRepository;
	
	@Override
	public void insertSanpham(Sanpham sp) {
		sp.setId(ThreadLocalRandom.current().nextLong(0, new Long("9000000000000000000")));
		this.sanphamRepository.insertSanpham(sp.getId(), sp.getMasanpham(), sp.getTensanpham(), sp.getImage(), sp.getCreateday(), sp.getCreateby(), sp.getUpdateday(), sp.getUpdateby(), sp.getXuatxu(), sp.getMota(), sp.getIsdelete(), sp.getLoaisanpham().getId());
	}
	
	@Override
	public int updateSanpham(Sanpham sp) {
		return this.sanphamRepository.updateSanpham(sp.getMasanpham(), sp.getTensanpham(), sp.getImage(), sp.getUpdateday(), sp.getUpdateby(), sp.getXuatxu(), sp.getMota(), sp.getIsdelete(), sp.getLoaisanpham().getId(), sp.getId());
	}


	@Override
	public Optional<Sanpham> finBySanphamId(Long id) {
		return sanphamRepository.finBySanphamId(id);
	}


	@Override
	public List<Sanpham> getAllSanpham() {
		return sanphamRepository.getAllSanpham();
	}
	
	
}
