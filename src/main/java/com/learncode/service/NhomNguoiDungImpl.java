package com.learncode.service;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learncode.models.ChucNang1;
import com.learncode.models.NhomNguoiDung;
import com.learncode.models.VaiTro;
import com.learncode.repository.ChucNang1Repository;
import com.learncode.repository.NhomNguoiDungRepository;
import com.learncode.repository.VaiTroRepository;

@Service
public class NhomNguoiDungImpl implements NhomNguoiDungService{
	
	@Autowired
	NhomNguoiDungRepository nhomNguoiDungRepository;

	@Autowired
	ChucNang1Repository chucNangRepository;
	
	@Autowired
	VaiTroRepository vaiTroRepository;
	
	
	@Override
	@Caching(
			put = @CachePut(value = "nhom"),
			evict = @CacheEvict(value = "nhom", allEntries = true)
			)
	public void insertNhomNguoiDung(NhomNguoiDung nnd) {
		this.nhomNguoiDungRepository.save(nnd);
//		this.nhomNguoiDungRepository.insertNhomNguoiDung(nnd.getId(), nnd.getManhom(), nnd.getTennhom(), nnd.getCreateday(), nnd.getNguoitao(), nnd.getUpdateday(), nnd.getNguoiupdate(), 0);
//		if (nnd.getChucnang() != null) {
//			for (ChucNang1 cn1 : nnd.getChucnang()) {
//				this.nhomNguoiDungRepository.insertNhomNguoiDungChucNang(nnd.getId(), cn1.getId());
//			}
//		}
	}

	@Override
	@Caching(
			put = @CachePut(value = "nhom"),
			evict = @CacheEvict(value = "nhom", allEntries = true)
			)
	public void updateNhomNguoiDung(NhomNguoiDung ndd) {
		this.nhomNguoiDungRepository.save(ndd);
//		this.nhomNguoiDungRepository.updateNhomNguoiDung(ndd.getManhom(), ndd.getTennhom(), ndd.getUpdateday(), ndd.getNguoiupdate(), ndd.getId());
//		this.nhomNguoiDungRepository.deleteNhomNguoiDungChucNang(ndd.getId());
//		if (ndd.getChucnang() != null) {
//			for (ChucNang1 cn1 : ndd.getChucnang()) {
//				this.nhomNguoiDungRepository.insertNhomNguoiDungChucNang(ndd.getId(), cn1.getId());
//			}
//		}
	}

	
	@Override
	public List<NhomNguoiDung> findByTennhom(String tennhom) {
		return this.nhomNguoiDungRepository.findByTennhom(tennhom);
	}

	@Override
	public void deleteNhomNguoiDung(NhomNguoiDung ndd) {
		this.nhomNguoiDungRepository.updateNhomNguoiDung(ndd.getManhom(), ndd.getTennhom(), ndd.getUpdateday(), ndd.getNguoiupdate(), ndd.getId());
	}

	@Override
	public List<NhomNguoiDung> findByManhom(String manhom) {
		return this.nhomNguoiDungRepository.findByManhom(manhom);
	}

	@Override
	public List<NhomNguoiDung> findAllNhomNguoiDung() {
		return this.nhomNguoiDungRepository.findAllNhomNguoiDung();
	}

	@Override
	@Cacheable(value = "nhom", key = "#id")
	public Optional<NhomNguoiDung> findByLongId(Long id) {
		return this.nhomNguoiDungRepository.findByLongId(id);
	}

	@Override
	public List<ChucNang1> findAllChucNang1(){
		return (List<ChucNang1>)this.chucNangRepository.findAllChucNang1();
	}
	
	@Override
	public List<VaiTro> findAllVaiTro(){
		return (List<VaiTro>)this.vaiTroRepository.findAll();
	}

	@Override
	public List<Long> findChucnangNhom(Long idnhom) {
		return this.nhomNguoiDungRepository.findChucnangNhom(idnhom);
	}

	
}
