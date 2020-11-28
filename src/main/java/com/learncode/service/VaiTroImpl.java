package com.learncode.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.learncode.models.ChucNang1;
import com.learncode.models.VaiTro;
import com.learncode.repository.ChucNang1Repository;
import com.learncode.repository.VaiTroRepository;

@Service
public class VaiTroImpl implements VaiTroService{
	

	@Autowired
	VaiTroRepository vaiTroRepository;
	
	@Autowired
	ChucNang1Service chucNang1Service;
	
	@Override
	public List<ChucNang1> finAllChucNang(){
		return this.chucNang1Service.findAllChucNang1();
	}
	
	@Override
	public void insertVaitro(VaiTro vt) {
		this.vaiTroRepository.save(vt);
//		this.vaiTroRepository.insertVaitro(vt.getId(), vt.getMavaitro(), vt.getTenvaitro(), vt.getNguoitao(), vt.getCreateday(), vt.getNguoiupdate(), vt.getUpdateday(), 0);
//		if (vt.getChucnang() != null) {
//			for (ChucNang1 cn1 : vt.getChucnang()) {
//				this.vaiTroRepository.insertVaitroVaChucnang(vt.getId(), cn1.getId());
//			}
//		}
	}

	@Override
	public List<VaiTro> listVaiTro() {
		return this.vaiTroRepository.listVaiTro();
	}

	@Override
	@Cacheable(value = "vaitro", key = "#vt.nguoiupdate")
	public void updateVaitro(VaiTro vt) {
		this.vaiTroRepository.save(vt);
//		this.vaiTroRepository.updateVaitro(vt.getMavaitro(), vt.getTenvaitro(), vt.getNguoiupdate(), vt.getUpdateday(), vt.getIsdelete(), vt.getId());
//		this.vaiTroRepository.deleteVaitroVaChucnang(vt.getId());
//		if (vt.getChucnang() != null) {
//			for (ChucNang1 cn : vt.getChucnang()) {
//				this.vaiTroRepository.insertVaitroVaChucnang(vt.getId(), cn.getId());
//			}
//		}
	}

	@Override
	@Cacheable(value = "vaitro", key = "#id")
	public Optional<VaiTro> findByVaitroId(Long id) {
		return this.vaiTroRepository.findByVaitroId(id);
	}

	@Override
	public List<VaiTro> findByMavaitro(String mavaitro) {
		return this.vaiTroRepository.findByMavaitro(mavaitro);
	}

	@Override
	public List<VaiTro> findByTenvaitro(String tenvaitro) {
		return this.vaiTroRepository.findByTenvaitro(tenvaitro);
	}

	@Override
	public List<Long> findChucnangVaitro(Long idvaitro) {
		return this.vaiTroRepository.findChucnangVaitro(idvaitro);
	}

	@Override
	public void updateDaXoa(VaiTro vt) {
		vaiTroRepository.updateDaXoa(vt.getNguoiupdate(), vt.getUpdateday() ,vt.getIsdelete(), vt.getId());
	}
	
	
}
