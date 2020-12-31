package com.learncode.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.learncode.models.LoaiSanPham;
import com.learncode.repository.LoaisanphamRepository;

@Component
@Service
public class LoaisanphamImpl implements LoaisanphamService {

	@Autowired
	LoaisanphamRepository loaisanphamRepository;

	@Override
//	@Caching(put = @CachePut(value = "loaisanpham"), evict = @CacheEvict(value = "loaisanpham", allEntries = true))
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
	@Caching(put = @CachePut(value = "loaisanpham"), evict = @CacheEvict(value = "loaisanpham", allEntries = true))
	public void updateLoaisanpham(LoaiSanPham lsp) {
		this.loaisanphamRepository.save(lsp);
	}

	@Override
	@Caching(
			put = @CachePut(value = "loaisanpham"),
			evict = @CacheEvict(value = "loaisanpham", allEntries = true)
			)
	public void deleteLoaisanpham(LoaiSanPham lsp) {
		loaisanphamRepository.deleteLoaisanpham(lsp.getUpdateday(), lsp.getNguoiupdate(), lsp.getIsdelete(),
				lsp.getId());
	}

	@Override
	public List<LoaiSanPham> searchTenLoaiSanPham(String tenloaisanpham) {
		return loaisanphamRepository.searchTenLoaiSanPham(tenloaisanpham);
	}

}
