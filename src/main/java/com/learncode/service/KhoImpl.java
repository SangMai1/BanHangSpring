package com.learncode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learncode.models.Kho;
import com.learncode.repository.KhoRepository;

@Service
public class KhoImpl implements KhoService {

	@Autowired
	KhoRepository khoRepository;

	@Override
	public void save(Kho k) {
		this.khoRepository.save(k);
	}
	
	@Override
	public void update(Kho k) {
		this.khoRepository.updateKho(k.getTrangthai(), k.getId());
	}

	@Override
	public List<Kho> findAll() {
		return this.khoRepository.findAll();
	}

	@Override
	public Optional<Kho> findById(Long id) {
		return this.khoRepository.findById(id);
	}
	
	
}
