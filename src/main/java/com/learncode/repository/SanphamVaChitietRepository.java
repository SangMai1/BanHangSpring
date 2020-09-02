package com.learncode.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.learncode.models.SanphamVaChitiet;

@Repository
@Transactional
public interface SanphamVaChitietRepository extends JpaRepository<SanphamVaChitiet, Long>{
	
	@Modifying
	@Query(value = "INSERT INTO public.qtht_sanphamvachitiet(id, idsanpham, kichthuoc, soluong, giatien, isdelete) VALUES (?, ?, ?, ?, ?, ?);", nativeQuery = true)
	int insertSanphamVaChitiet(@Param("id") Long id, @Param("idsanpham") Long idsanpham, @Param("kichthuoc") String kichthuoc, @Param("soluong") Integer soluong, @Param("giatien") Float giatien, @Param("isdelete") Integer isdelete);
	
	@Modifying
	@Query(value = "UPDATE public.qtht_sanphamvachitiet SET kichthuoc=?, soluong=?, giatien=?, isdelete=? WHERE id = ?;", nativeQuery = true)
	int updateSanphamVaChitiet(@Param("kichthuoc") String kichthuoc, @Param("soluong") Integer soluong, @Param("giatien") Float giatien, @Param("isdelete") Integer isdelete, @Param("id") Long id);
	
	@Query(value = "SELECT id, idsanpham, kichthuoc, soluong, giatien, isdelete FROM qtht_sanphamvachitiet WHERE id = ?", nativeQuery = true)
	Optional<SanphamVaChitiet> findBySanphamVaChitietId(@Param("id") Long id);
	
	@Query(value = "SELECT id, idsanpham, kichthuoc, soluong, giatien, isdelete FROM qtht_sanphamvachitiet WHERE isdelete = 0", nativeQuery = true)
	List<SanphamVaChitiet> getAll();
}
