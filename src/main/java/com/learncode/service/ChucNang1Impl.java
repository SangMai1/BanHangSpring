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

@Component
@Service
public class ChucNang1Impl implements ChucNang1Service{
	
	@Autowired
	ChucNang1Repository chucNangRepository;
	
	@Override
	public List<ChucNang1> findAllChucNang1() {
		return chucNangRepository.findAllChucNang1();
	}

	@Override
	public List<ChucNang1> getAllChucNang1() {
		return chucNangRepository.getAllChucNang1();
	}
	
	@Override
	public List<ChucNang1> getAllChucNang1Parent(){
		return chucNangRepository.getAllChucNang1Parent();
	}
	
	
	@Override
	@Cacheable(value = "chucnang", key = "#id")
	public Optional<ChucNang1> findByChucNangEditId(Long id){
		System.out.println("find id");
		return this.chucNangRepository.findByChucNangEditId(id);
	}


	@Override
	public Optional<ChucNang1> findById(Long id) {
		return chucNangRepository.findById(id);
	}

	@Override
	public List<ChucNang1> findByTenchucnang(String tenchucnang) {
		return chucNangRepository.findByTenchucnang(tenchucnang);
	}

	@Override
	@Caching(
			put = @CachePut(value = "chucnang"),
			evict = @CacheEvict(value = "chucnang", allEntries = true )
			)
	public void insertChucNang1(ChucNang1 cn) {
		System.out.println("save 1");
		this.chucNangRepository.save(cn);
	}

	@Override
	@Caching(
			put = @CachePut(value = "chucnang"),
			evict = @CacheEvict(value = "chucnang", allEntries = true))
	public void updateChucNang1(ChucNang1 cn) {
		System.out.println("update day");
		this.chucNangRepository.save(cn);
	}

	@Override
	public long count(Long id) {
		return chucNangRepository.count(id);
	}

	@Override
	public List<ChucNang1> findByMachucnang(String machucnang) {
		return chucNangRepository.findByMachucnang(machucnang);
	}

	@Override
	public List<ChucNang1> findChucnangByTennguoidung(String tennguoidung) {
		return chucNangRepository.findChucnangByTennguoidung(tennguoidung, tennguoidung);
	}

	@Override
	public List<String> maapi() {
		return chucNangRepository.maapi();
	}
	
	
}
