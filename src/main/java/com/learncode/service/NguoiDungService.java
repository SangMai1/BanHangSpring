package com.learncode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.learncode.models.ChucNang1;
import com.learncode.models.Nguoidung;
import com.learncode.models.NhomNguoiDung;
import com.learncode.models.VaiTro;


public interface NguoiDungService {

	List<NhomNguoiDung> findAllNhom();

	List<VaiTro> finAllVaiTro();

	List<ChucNang1> finAllChucNang();

	List<Nguoidung> getAllNguoiDung();

	void insertNguoidung(Nguoidung nd);

	//boolean checkLogin(String username, String password);

	List<Nguoidung> findByTennguoidung(String tennguoidung);

	Nguoidung findUrlChucNang(String tennguoidung);

//	boolean checkUrl(Nguoidung nd, String url);

	void updateNguoidung(Nguoidung nd);

	Optional<Nguoidung> findNguoidungById(Long id);

	void deleteNguoidung(Nguoidung nd);

	Nguoidung findByTen(String tennguoidung);


	List<Long> findByIdvaitro(Long idnguoidung);

	List<Long> findByIdnhom(Long idnguoidung);

	Nguoidung findById1(Long id);

	Nguoidung findUrl(String tennguoidung);

	List<String> findUrlNd(String tennguoidung);

}
