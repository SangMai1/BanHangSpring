package com.learncode.service;

import java.util.List;
import java.util.Optional;

import com.learncode.models.SanphamVaChitiet;

public interface SanphamVaChitietService {

	int insertSanphamVaChitiet(SanphamVaChitiet spvct);

	List<SanphamVaChitiet> getAll();

	Optional<SanphamVaChitiet> findBySanphamVaChitietId(Long id);

	int updateSanphamVaChitiet(String kichthuoc, Integer soluong, Float giatien, Integer isdelete, Long id);

	List<SanphamVaChitiet> findBySizeSanpham(Long idsanpham);

}
