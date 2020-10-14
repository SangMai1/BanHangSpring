package com.learncode.service;

import java.util.List;
import java.util.Optional;

import com.learncode.models.ChucNang1;

public interface ChucNang1Service {

	int insertChucNang1(ChucNang1 cn);

	int updateChucNang1(ChucNang1 cn);

	int deleteChucNang1(List<ChucNang1> cn);

	List<ChucNang1> findAllChucNang1();

	List<ChucNang1> findByTenchucnang(String tenchucnang);

	List<ChucNang1> getAllChucNang1();

	List<ChucNang1> getAllChucNang1Parent();

	Optional<ChucNang1> findByChucNangEditId(Long id);

	long count(Long id);

	List<ChucNang1> findByMachucnang(String machucnang);

	Optional<ChucNang1> findById(Long id);

	List<ChucNang1> findChucnangByTennguoidung(String tennguoidung);

	List<String> maapi();

}
