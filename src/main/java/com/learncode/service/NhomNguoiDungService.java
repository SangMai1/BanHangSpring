package com.learncode.service;

import java.util.List;
import java.util.Optional;

import com.learncode.models.ChucNang1;
import com.learncode.models.NhomNguoiDung;
import com.learncode.models.VaiTro;

public interface NhomNguoiDungService {

	List<ChucNang1> findAllChucNang1();

	void insertNhomNguoiDung(NhomNguoiDung ndd);

	void updateNhomNguoiDung(NhomNguoiDung ndd);

	List<NhomNguoiDung> findAllNhomNguoiDung();

	void deleteNhomNguoiDung(NhomNguoiDung ndd);

	Optional<NhomNguoiDung> findByLongId(Long id);

	List<NhomNguoiDung> findByTennhom(String tennhom);

	List<VaiTro> findAllVaiTro();

	List<NhomNguoiDung> findByManhom(String manhom);

	List<Long> findChucnangNhom(Long idnhom);

}
