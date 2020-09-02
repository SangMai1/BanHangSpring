package com.learncode.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learncode.models.VaiTro;
import com.learncode.repository.VaiTroRepository;

@Service
public class VaiTroImpl implements VaiTroService{
	
	@Autowired
	VaiTroRepository vaiTroRepository;

	@Override
	public int insertVaitro(VaiTro vt) {
		return vaiTroRepository.insertVaitro(vt.getId(), vt.getMavaitro(), vt.getTenvaitro(), vt.getNguoitao(), vt.getCreateday(), vt.getNguoiupdate(), vt.getUpdateday(), 0);
	}

	@Override
	public List<VaiTro> listVaiTro() {
		return vaiTroRepository.listVaiTro();
	}

	@Override
	public int updateVaitro(VaiTro vt) {
		return vaiTroRepository.updateVaitro(vt.getMavaitro(), vt.getTenvaitro(), vt.getNguoiupdate(), vt.getUpdateday(), vt.getIsdelete(), vt.getId());
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
	
	
}
