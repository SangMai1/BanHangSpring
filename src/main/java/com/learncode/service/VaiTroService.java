package com.learncode.service;

import java.util.List;
import java.util.Optional;

import com.learncode.models.ChucNang1;
import com.learncode.models.VaiTro;

public interface VaiTroService {

	void insertVaitro(VaiTro vt);

	List<VaiTro> listVaiTro();


	Optional<VaiTro> findByVaitroId(Long id);

	List<VaiTro> findByTenvaitro(String tenvaitro);

	List<VaiTro> findByMavaitro(String mavaitro);

	List<ChucNang1> finAllChucNang();

	List<Long> findChucnangVaitro(Long idvaitro);

	void updateVaitro(VaiTro vt);

}
