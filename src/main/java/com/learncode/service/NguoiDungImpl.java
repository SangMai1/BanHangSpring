package com.learncode.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void insertNguoidung(Nguoidung nd) {
	   this.nguoiDungRepository.insertNguoidung(nd.getId(), nd.getManguoidung(), nd.getTennguoidung(), Xuly.giaiMd5(nd.getPassword()), nd.getEmail(), nd.getGender(), nd.getPhone(), nd.getCreateday(), nd.getNguoitao(), nd.getUpdateday(), nd.getNguoiupdate(), nd.getIsdelete());
		
		if (nd.getChucnang() != null) {
			for (ChucNang1 cn1 : nd.getChucnang()) {
				this.nguoiDungRepository.insertNguoidungVaChucnang(nd.getId(), cn1.getId());
			}
		}
		
		for (VaiTro vt : nd.getVaitro()) {
			this.nguoiDungRepository.insertNguoidungVaVaitro(nd.getId(), vt.getId());
		}
		
		for (NhomNguoiDung nnd : nd.getNhomnguoidung()) {
			this.nguoiDungRepository.insertNguoidungVaNhomnguoidung(nd.getId(), nnd.getId());
		}
	}

	@Override
	public void updateNguoidung(Nguoidung nd) {
		this.nguoiDungRepository.updateNguoidung(nd.getManguoidung(), nd.getTennguoidung(), nd.getPassword(), nd.getEmail(), nd.getGender(), nd.getPhone(), nd.getUpdateday(), nd.getNguoiupdate(), nd.getIsdelete(), nd.getId());
		this.nguoiDungRepository.deleteNguoidungVaChucnang(nd.getId());
		this.nguoiDungRepository.deleteNguoidungVaNhomnguoidung(nd.getId());
		this.nguoiDungRepository.deleteNguoidungVaVaitro(nd.getId());
		
		if (nd.getChucnang() != null) {
			for (ChucNang1 cn1 : nd.getChucnang()) {
				this.nguoiDungRepository.insertNguoidungVaChucnang(nd.getId(), cn1.getId());
			}
		}
		
		for (NhomNguoiDung nnd : nd.getNhomnguoidung()) {
			this.nguoiDungRepository.insertNguoidungVaNhomnguoidung(nd.getId(), nnd.getId());
		}
		
		for (VaiTro vt : nd.getVaitro()) {
			this.nguoiDungRepository.insertNguoidungVaVaitro(nd.getId(), vt.getId());
		}
	}
	
	@Override
	public Nguoidung findByTen(String tennguoidung) {
		return nguoiDungRepository.findByTen(tennguoidung);
	}

	@Override
	public void deleteNguoidung(Nguoidung nd) {
		this.nguoiDungRepository.updateNguoidung(nd.getManguoidung(), nd.getTennguoidung(), nd.getPassword(), nd.getEmail(), nd.getGender(), nd.getPhone(), nd.getUpdateday(), nd.getNguoiupdate(), nd.getIsdelete(), nd.getId());
	}
	
	
	@Override
	public Optional<Nguoidung> findNguoidungById(Long id) {
		return nguoiDungRepository.findNguoidungById(id);
	}

	@Override
	public List<Nguoidung> getAllNguoiDung() {
		return nguoiDungRepository.getAllNguoiDung();
	}
	
	
	@Override
	public List<Nguoidung> findByTennguoidung(String tennguoidung){
		return this.nguoiDungRepository.findByTennguoidung(tennguoidung);
	}
	
	@Override
	public Nguoidung findUrlChucNang(String tennguoidung){
		Nguoidung nd = this.findByTen(tennguoidung);
		List<ChucNang1> dscn = this.chucNang1Service.findChucnangByTennguoidung(tennguoidung);
		nd.setChucnang(dscn);
		return nd;
	}
	
	
	@Override
	public List<Long> findByIdchucnang(Long idnguoidung) {
		return nguoiDungRepository.findByIdchucnang(idnguoidung);
	}

	
	@Override
	public List<Long> findByIdnhom(Long idnguoidung) {
		return nguoiDungRepository.findByIdnhom(idnguoidung);
	}

	@Override
	public List<Long> findByIdvaitro(Long idnguoidung) {
		return nguoiDungRepository.findByIdvaitro(idnguoidung);
	}

	
	@Override
	public Nguoidung findById1(Long id) {
		return nguoiDungRepository.findById1(id);
	}

	@Override
	public boolean checkLogin(String username, String password) {
		Nguoidung nd = this.nguoiDungRepository.findByTen(username);
		if (username != null || Xuly.checkMd5(password, nd.getPassword())) {
			return true;
		}
		return false;
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
