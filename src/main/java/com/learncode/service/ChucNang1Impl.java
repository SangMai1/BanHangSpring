package com.learncode.service;

import java.sql.Timestamp;
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
import org.springframework.transaction.annotation.Transactional;
import com.learncode.models.ChucNang1;
import com.learncode.repository.ChucNang1Repository;

@Service
public class ChucNang1Impl implements ChucNang1Service{
	
	private final ChucNang1Repository chucNang1Repository;
	
	@Autowired
	public ChucNang1Impl(ChucNang1Repository chucNang1Repository) {
		this.chucNang1Repository = chucNang1Repository;
	}
	
	@Override
	public List<ChucNang1> findAllChucNang1() {
		return chucNang1Repository.findAllChucNang1();
	}

	@Override
	public List<ChucNang1> getAllChucNang1() {
		return chucNang1Repository.getAllChucNang1();
	}
	
	@Override
	public List<ChucNang1> getAllChucNang1Parent(){
		return chucNang1Repository.getAllChucNang1Parent();
	}
	
	
	@Override
	@Cacheable(value = "chucnang", key = "#id")
	public Optional<ChucNang1> findByChucNangEditId(Long id){
		System.out.println("find id");
		return this.chucNang1Repository.findByChucNangEditId(id);
	}


	@Override
	public Optional<ChucNang1> findById(Long id) {
		return chucNang1Repository.findById(id);
	}

	@Override
	public List<ChucNang1> findByTenchucnang(String tenchucnang) {
		return chucNang1Repository.findByTenchucnang(tenchucnang);
	}

	@Override
	@Caching(
			put = @CachePut(value = "chucnang"),
			evict = @CacheEvict(value = "chucnang", allEntries = true )
			)
	public void insertChucNang1(ChucNang1 cn) {
		System.out.println("save 1");
		this.chucNang1Repository.save(cn);
	}

	@Override
	@Caching(
			put = @CachePut(value = "chucnang"),
			evict = @CacheEvict(value = "chucnang", allEntries = true))
	public void updateChucNang1(ChucNang1 cn) {
		System.out.println("update day");
		this.chucNang1Repository.save(cn);
	}

	@Override
	public long count(Long id) {
		return chucNang1Repository.count(id);
	}

	@Override
	public List<ChucNang1> findByMachucnang(String machucnang) {
		return chucNang1Repository.findByMachucnang(machucnang);
	}

	@Override
	public List<ChucNang1> findChucnangByTennguoidung(String tennguoidung) {
		return chucNang1Repository.findChucnangByTennguoidung(tennguoidung, tennguoidung);
	}

	@Override
	public List<String> maapi() {
		return chucNang1Repository.maapi();
	}
	
	
}
