package com.learncode.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learncode.comon.Xuly;
import com.learncode.models.ChucNang1;
import com.learncode.models.Nguoidung;
import com.learncode.models.NhomNguoiDung;
import com.learncode.models.VaiTro;
import com.learncode.repository.ChucNang1Repository;
import com.learncode.repository.NguoiDungRepository;
import com.learncode.repository.NhomNguoiDungRepository;
import com.learncode.repository.VaiTroRepository;

@Component
@Service
public class NguoiDungImpl implements NguoiDungService{
	
	@Autowired
	NguoiDungRepository nguoiDungRepository;
	
	@Autowired
	NhomNguoiDungRepository nhomNguoiDungRepository;
	
	@Autowired
	ChucNang1Repository chucNangRepository;
	
	@Autowired
	ChucNang1Service chucNang1Service;
	
	@Autowired
	VaiTroRepository vaiTroRepository;
	
	@Override
	public List<NhomNguoiDung> findAllNhom(){
		return this.nhomNguoiDungRepository.findAllNhomNguoiDung();
	}
	
	@Override
	public List<ChucNang1> finAllChucNang(){
		return this.chucNang1Service.findAllChucNang1();
	}

	@Override
	public List<VaiTro> finAllVaiTro(){
		return this.vaiTroRepository.listVaiTro();
	}

	
	@Override
	@Caching(
			put = @CachePut(value = "nguoidung"),
			evict = @CacheEvict(value = "nguoidung", allEntries = true)
			)	
	public void insertNguoidung(Nguoidung nd) {
		this.nguoiDungRepository.save(nd);
//	   this.nguoiDungRepository.insertNguoidung(nd.getId(), nd.getManguoidung(), nd.getTennguoidung(), Xuly.giaiMd5(nd.getPassword()), nd.getEmail(), nd.getGender(), nd.getPhone(), nd.getCreateday(), nd.getNguoitao(), nd.getUpdateday(), nd.getNguoiupdate(), nd.getIsdelete());
//		
//	   if (nd.getVaitro() != null) {
//			for (VaiTro vt : nd.getVaitro()) {
//				this.nguoiDungRepository.insertNguoidungVaVaitro(nd.getId(), vt.getId());
//			}
//	   }
//
//		if (nd.getNhomnguoidung() != null) {
//			for (NhomNguoiDung nnd : nd.getNhomnguoidung()) {
//				this.nguoiDungRepository.insertNguoidungVaNhomnguoidung(nd.getId(), nnd.getId());
//			}
//		}

	}

	@Override
	@Caching(
			put = @CachePut(value = "nguoidung"),
			evict = @CacheEvict(value = "nguoidung", allEntries = true)
			)
	public void updateNguoidung(Nguoidung nd) {
		this.nguoiDungRepository.save(nd);
//		this.nguoiDungRepository.updateNguoidung(nd.getManguoidung(), nd.getTennguoidung(), nd.getPassword(), nd.getEmail(), nd.getGender(), nd.getPhone(), nd.getUpdateday(), nd.getNguoiupdate(), nd.getIsdelete(), nd.getId());
//		this.nguoiDungRepository.deleteNguoidungVaNhomnguoidung(nd.getId());
//		this.nguoiDungRepository.deleteNguoidungVaVaitro(nd.getId());
//		
//		if (nd.getNhomnguoidung() != null) {
//			for (NhomNguoiDung nnd : nd.getNhomnguoidung()) {
//				this.nguoiDungRepository.insertNguoidungVaNhomnguoidung(nd.getId(), nnd.getId());
//			}
//		}
//
//		if (nd.getVaitro() != null) {
//			for (VaiTro vt : nd.getVaitro()) {
//				this.nguoiDungRepository.insertNguoidungVaVaitro(nd.getId(), vt.getId());
//			}
//		}

	}
	
	@Override
	public Nguoidung findByTen(String tennguoidung) {
		return this.nguoiDungRepository.findByTen(tennguoidung);
	}

	@Override
	@Caching(
			put = @CachePut(value = "nguoidung"),
			evict = @CacheEvict(value = "nguoidung", allEntries = true)
			)
	public void deleteNguoidung(Nguoidung nd) {
		this.nguoiDungRepository.updateNguoidung(nd.getManguoidung(), nd.getTennguoidung(), nd.getPassword(), nd.getEmail(), nd.getGender(), nd.getPhone(), nd.getUpdateday(), nd.getNguoiupdate(), nd.getIsdelete(), nd.getId());
	}
	
	
	@Override
	@Cacheable(value = "nguoidung", key = "#id")
	public Optional<Nguoidung> findNguoidungById(Long id) {
		return this.nguoiDungRepository.findNguoidungById(id);
	}

	@Override
	public List<Nguoidung> getAllNguoiDung() {
		return this.nguoiDungRepository.getAllNguoiDung();
	}
	
	
	@Override
	public List<Nguoidung> findByTennguoidung(String tennguoidung){
		return this.nguoiDungRepository.findByTennguoidung(tennguoidung);
	}
	
	@Override
	public Nguoidung findUrlChucNang(String tennguoidung){
		Nguoidung nd = this.findByTen(tennguoidung);
		return nd;
	}
	


	
	@Override
	public List<Long> findByIdnhom(Long idnguoidung) {
		return this.nguoiDungRepository.findByIdnhom(idnguoidung);
	}

	@Override
	public List<Long> findByIdvaitro(Long idnguoidung) {
		return this.nguoiDungRepository.findByIdvaitro(idnguoidung);
	}

	
	@Override
	public Nguoidung findById1(Long id) {
		return this.nguoiDungRepository.findById1(id);
	}

//	@Override
//	public boolean checkLogin(String username, String password) {
//		Nguoidung nd = this.nguoiDungRepository.findByTen(username);
//		if (username != null || Xuly.checkMd5(password, nd.getPassword())) {
//			return true;
//		}
//		return false;
//	}

	@Override
	public Nguoidung findUrl(String tennguoidung) {
		return nguoiDungRepository.findUrl(tennguoidung, tennguoidung);
	}

	@Override
	public List<String> findUrlNd(String tennguoidung) {
		return nguoiDungRepository.findUrlNd(tennguoidung);
	}
	
	
//	@Override
//	public boolean checkUrl(Nguoidung nd, String url) {
//		for (ChucNang1 chucnang : nd.getChucnang()) {
//			if (chucnang.getMaapi().equals(url)) {
//				return true;
//			}
//		}
//		return false;
//	}
}
