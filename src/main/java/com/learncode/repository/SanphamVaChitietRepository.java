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
	@Query(value = "INSERT INTO public.qtht_sanphamvachitiet(id, idsanpham, kichthuoc, soluong, giatien, giamgia, isdelete) VALUES (?, ?, ?, ?, ?, ?, ?);", nativeQuery = true)
	int insertSanphamVaChitiet(@Param("id") Long id, @Param("idsanpham") Long idsanpham, @Param("kichthuoc") String kichthuoc, @Param("soluong") Integer soluong, @Param("giatien") Float giatien, @Param("giamgia") Integer giamgia, @Param("isdelete") Integer isdelete);
	
	@Modifying
	@Query(value = "UPDATE public.qtht_sanphamvachitiet SET kichthuoc=?, soluong=?, giatien=?, giamgia=?, isdelete=? WHERE id = ?;", nativeQuery = true)
	int updateSanphamVaChitiet(@Param("kichthuoc") String kichthuoc, @Param("soluong") Integer soluong, @Param("giatien") Float giatien, @Param("giamgia") Integer giamgia, @Param("isdelete") Integer isdelete, @Param("id") Long id);
	
	@Query(value = "SELECT id, idsanpham, kichthuoc, soluong, giatien, giamgia, isdelete FROM qtht_sanphamvachitiet WHERE id=? AND isdelete = 0", nativeQuery = true)
	Optional<SanphamVaChitiet> findBySanphamVaChitietId(Long id);
	
	@Query(value = "SELECT id, idsanpham, kichthuoc, soluong, giatien, giamgia, isdelete FROM qtht_sanphamvachitiet WHERE isdelete = 0", nativeQuery = true)
	List<SanphamVaChitiet> getAll();
	
	@Query(value = "SELECT id, idsanpham, kichthuoc, soluong, giatien, isdelete, giamgia FROM qtht_sanphamvachitiet WHERE idsanpham=? and isdelete = 0", nativeQuery = true)
	List<SanphamVaChitiet> findBySizeSanpham(Long idsanpham);
	
	@Query(value = "SELECT spvct.id, spvct.idsanpham,sp.id, sp.masanpham, sp.tensanpham, sp.image, sp.mota, spvct.kichthuoc, spvct.soluong, spvct.giatien, spvct.giamgia, sp.isdelete  \r\n" + 
			"						FROM qtht_sanphamvachitiet spvct \r\n" + 
			"						LEFT JOIN qtht_sanpham sp ON sp.id = spvct.idsanpham \r\n" + 
			"						WHERE spvct.id = ? AND sp.isdelete = 0 AND spvct.isdelete = 0", nativeQuery = true)
	Optional<SanphamVaChitiet> findBySanphamId(Long id);
	
	@Query(value = "SELECT spvct.id, spvct.idsanpham,sp.id, sp.masanpham, sp.tensanpham, sp.image, sp.mota, spvct.kichthuoc, spvct.soluong, spvct.giatien, spvct.giamgia, sp.isdelete  \r\n" + 
			"									FROM qtht_sanphamvachitiet spvct \r\n" + 
			"									LEFT JOIN qtht_sanpham sp ON sp.id = spvct.idsanpham \r\n" + 
			"									WHERE sp.isdelete = 0 AND spvct.isdelete = 0 AND spvct.giatien BETWEEN ? AND ?", nativeQuery = true)
	List<SanphamVaChitiet> searchGiatien(@Param("min") float min, @Param("max") float max);
}
