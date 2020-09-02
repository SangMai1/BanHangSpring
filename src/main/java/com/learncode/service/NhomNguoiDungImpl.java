package com.learncode.service;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learncode.models.ChucNang1;
import com.learncode.models.NhomNguoiDung;
import com.learncode.models.VaiTro;
import com.learncode.repository.ChucNang1Repository;
import com.learncode.repository.NhomNguoiDungRepository;
import com.learncode.repository.VaiTroRepository;

@Service
@Transactional
public class NhomNguoiDungImpl implements NhomNguoiDungService{
	
	@Autowired
	NhomNguoiDungRepository nhomNguoiDungRepository;

	@Autowired
	ChucNang1Repository chucNangRepository;
	
	@Autowired
	VaiTroRepository vaiTroRepository;
	
	
	@Override
	public void insertNhomNguoiDung(NhomNguoiDung nnd) {
		this.nhomNguoiDungRepository.insertNhomNguoiDung(nnd.getId(), nnd.getManhom(), nnd.getTennhom(), nnd.getCreateday(), nnd.getNguoitao(), nnd.getUpdateday(), nnd.getNguoiupdate(), 0);
		if (nnd.getChucnang() != null) {
			for (ChucNang1 cn1 : nnd.getChucnang()) {
				this.nhomNguoiDungRepository.insertNhomNguoiDungChucNang(nnd.getId(), cn1.getId());
			}
		}
	}

	@Override
	public void updateNhomNguoiDung(NhomNguoiDung ndd) {
		this.nhomNguoiDungRepository.updateNhomNguoiDung(ndd.getManhom(), ndd.getTennhom(), ndd.getUpdateday(), ndd.getNguoiupdate(), ndd.getId());
		this.nhomNguoiDungRepository.deleteNhomNguoiDungChucNang(ndd.getId());
		if (ndd.getChucnang() != null) {
			for (ChucNang1 cn1 : ndd.getChucnang()) {
				this.nhomNguoiDungRepository.insertNhomNguoiDungChucNang(ndd.getId(), cn1.getId());
			}
		}
	}

	
	@Override
	public List<NhomNguoiDung> findByTennhom(String tennhom) {
		return nhomNguoiDungRepository.findByTennhom(tennhom);
	}

	@Override
	public void deleteNhomNguoiDung(NhomNguoiDung ndd) {
		ndd.setUpdateday(new Timestamp(new Date().getTime()));
		this.nhomNguoiDungRepository.updateNhomNguoiDung(ndd.getManhom(), ndd.getTennhom(), ndd.getUpdateday(), ndd.getNguoiupdate(), ndd.getId());
	}

	@Override
	public List<NhomNguoiDung> findByManhom(String manhom) {
		return nhomNguoiDungRepository.findByManhom(manhom);
	}

	@Override
	public List<NhomNguoiDung> findAllNhomNguoiDung() {
		return nhomNguoiDungRepository.findAllNhomNguoiDung();
	}

	@Override
	public Optional<NhomNguoiDung> findByLongId(Long id) {
		return nhomNguoiDungRepository.findByLongId(id);
	}

	@Override
	public List<ChucNang1> findAllChucNang1(){
		return (List<ChucNang1>)chucNangRepository.findAllChucNang1();
	}
	
	@Override
	public List<VaiTro> findAllVaiTro(){
		return (List<VaiTro>)vaiTroRepository.findAll();
	}

}
