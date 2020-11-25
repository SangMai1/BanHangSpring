package com.learncode.service;

import java.util.List;
import java.util.Optional;

import com.learncode.models.SanphamVaChitiet;

public interface SanphamVaChitietService {

	void insertSanphamVaChitiet(SanphamVaChitiet spvct);

	List<SanphamVaChitiet> getAll();

	Optional<SanphamVaChitiet> findBySanphamVaChitietId(Long id);

	void updateSanphamVaChitiet(SanphamVaChitiet spvct);

	List<SanphamVaChitiet> findBySizeSanpham(Long idsanpham);

	Optional<SanphamVaChitiet> findBySanphamId(Long id);

	List<SanphamVaChitiet> searchGiatien(float min, float max);

	List<SanphamVaChitiet> getSanphammoi();

	List<SanphamVaChitiet> getSanphamnoibat();

	List<SanphamVaChitiet> getTatcasanpham();

	List<SanphamVaChitiet> getAllSanphambanchay();

	List<SanphamVaChitiet> getAllSanphamsale();

	List<SanphamVaChitiet> getAllSanphamnoibat();

	List<SanphamVaChitiet> getAllSanphammoi();

	List<SanphamVaChitiet> getSearchSize(String size);

	List<SanphamVaChitiet> searchKichThuoc(String kichthuoc);


}
