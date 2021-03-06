package com.learncode.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.learncode.models.Sanpham;
import com.learncode.repository.SanphamRepository;

import groovyjarjarpicocli.CommandLine.Command;

@Service
public class SanphamImpl implements SanphamService{
	
	private final SanphamRepository sanphamRepository;
	
	@Autowired
	public SanphamImpl(SanphamRepository sanphamRepository) {
		this.sanphamRepository = sanphamRepository;
	}
	@Override
	@Caching(
			put = @CachePut(value = "sanpham"),
			evict = @CacheEvict(value = "sanpham", allEntries = true)
			)
	public void insertSanpham(Sanpham sp) {
		this.sanphamRepository.save(sp);
	}
	
	@Override
	@Caching(
			put = @CachePut(value = "sanpham"),
			evict = @CacheEvict(value = "sanpham", allEntries = true)
			)
	public void updateSanpham(Sanpham sp) {
		this.sanphamRepository.save(sp);
	}


	@Override
	@Cacheable(value = "sanpham", key = "#id")
	public Optional<Sanpham> finBySanphamId(Long id) {
		return this.sanphamRepository.finBySanphamId(id);
	}


	@Override
	public List<Sanpham> getAllSanpham() {
		return this.sanphamRepository.getAllSanpham();
	}

	@Override
	public List<Sanpham> getSanphamAndSanphamchitiet() {
		return this.sanphamRepository.getSanphamAndSanphamchitiet();
	}

	@Override
	public List<Sanpham> getSanphammoi() {
		return this.sanphamRepository.getSanphammoi();
	}

	@Override
	public List<Sanpham> searchGiatien(float min, float max) {
		return sanphamRepository.searchGiatien(min, max);
	}

	@Override
	public List<Sanpham> searchSize(String size) {
		return sanphamRepository.searchSize(size);
	}

	@Override
	@Caching(
			put = @CachePut(value = "sanpham"),
			evict = @CacheEvict(value = "sanpham", allEntries = true)
			)
	public void deleteSanpham(Sanpham sp) {
		sanphamRepository.deleteSanpham(sp.getUpdateday(), sp.getNguoiupdate(), sp.getIsdelete(), sp.getId());
	}

	@Override
	public List<Sanpham> searchTenSanPham(String tensanpham) {
		return sanphamRepository.searchTenSanPham(tensanpham);
	}


	
}
