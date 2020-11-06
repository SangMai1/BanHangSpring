package com.learncode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.learncode.models.ChucNang1;


public interface ChucNang1Service {

	void insertChucNang1(ChucNang1 cn);

	void updateChucNang1(ChucNang1 cn);


	List<ChucNang1> findAllChucNang1();

	List<ChucNang1> findByTenchucnang(String tenchucnang);

	List<ChucNang1> getAllChucNang1();

	List<ChucNang1> getAllChucNang1Parent();


	long count(Long id);

	List<ChucNang1> findByMachucnang(String machucnang);

	Optional<ChucNang1> findById(Long id);

	List<ChucNang1> findChucnangByTennguoidung(String tennguoidung);

	List<String> maapi();

	Optional<ChucNang1> findByChucNangEditId(Long id) ;

}
