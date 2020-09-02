package com.learncode.service;

import java.util.List;
import java.util.Optional;

import com.learncode.models.VaiTro;

public interface VaiTroService {

	int insertVaitro(VaiTro vt);

	List<VaiTro> listVaiTro();

	int updateVaitro(VaiTro vt);

	Optional<VaiTro> findByVaitroId(Long id);

	List<VaiTro> findByTenvaitro(String tenvaitro);

	List<VaiTro> findByMavaitro(String mavaitro);

}
