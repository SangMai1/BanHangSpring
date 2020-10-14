package com.learncode.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
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
	ChucNang1Repository chucNangRepository;
	
	@Autowired
	ChucNang1Service chucNang1Service;
	
	@Override
	public List<ChucNang1> finAllChucNang(){
		return this.chucNang1Service.findAllChucNang1();
	}
	
	@Override
	public void insertVaitro(VaiTro vt) {
		this.vaiTroRepository.insertVaitro(vt.getId(), vt.getMavaitro(), vt.getTenvaitro(), vt.getNguoitao(), vt.getCreateday(), vt.getNguoiupdate(), vt.getUpdateday(), 0);
		if (vt.getChucnang() != null) {
			for (ChucNang1 cn1 : vt.getChucnang()) {
				this.vaiTroRepository.insertVaitroVaChucnang(vt.getId(), cn1.getId());
			}
		}
	}

	@Override
	public List<VaiTro> listVaiTro() {
		return vaiTroRepository.listVaiTro();
	}

	@Override
	public void updateVaitro(VaiTro vt) {
		this.vaiTroRepository.updateVaitro(vt.getMavaitro(), vt.getTenvaitro(), vt.getNguoiupdate(), vt.getUpdateday(), vt.getIsdelete(), vt.getId());
		this.vaiTroRepository.deleteVaitroVaChucnang(vt.getId());
		if (vt.getChucnang() != null) {
			for (ChucNang1 cn : vt.getChucnang()) {
				this.vaiTroRepository.insertVaitroVaChucnang(vt.getId(), cn.getId());
			}
		}
	}

	@Override
	public Optional<VaiTro> findByVaitroId(Long id) {
		return vaiTroRepository.findByVaitroId(id);
	}

	@Override
	public List<VaiTro> findByMavaitro(String mavaitro) {
		return vaiTroRepository.findByMavaitro(mavaitro);
	}

	@Override
	public List<VaiTro> findByTenvaitro(String tenvaitro) {
		return vaiTroRepository.findByTenvaitro(tenvaitro);
	}

	@Override
	public List<Long> findChucnangVaitro(Long idvaitro) {
		return vaiTroRepository.findChucnangVaitro(idvaitro);
	}
	
	
}
