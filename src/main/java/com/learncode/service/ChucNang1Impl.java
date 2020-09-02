package com.learncode.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.learncode.models.ChucNang1;
import com.learncode.repository.ChucNang1Repository;

@Service
@Transactional
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
	public Optional<ChucNang1> findByChucNangEditId(Long id){
		return this.chucNangRepository.findByChucNangEditId(id);
	}

	@Override
	public int deleteChucNang1(List<ChucNang1> cn) {
		for (ChucNang1 chucNang1 : cn) {
			this.chucNangRepository.deleteChucNang1(chucNang1.getId());
		}
		return chucNangRepository.deleteChucNang1(cn.get(0).getId());
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
	public int insertChucNang1(ChucNang1 cn) {
		return chucNangRepository.insertChucNang1(cn.getId(), cn.getMachucnang(), cn.getTenchucnang(), cn.getMaapi(), cn.getCreateday(), cn.getNguoitao(), cn.getUpdateday(), cn.getNguoiupdate(), cn.getParentid(), 0);
	}

	@Override
	public int updateChucNang1(ChucNang1 cn) {
		return chucNangRepository.updateChucNang1(cn.getMachucnang(), cn.getTenchucnang(), cn.getMaapi(), cn.getUpdateday(), cn.getNguoiupdate(), cn.getId());
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
	
	
}
