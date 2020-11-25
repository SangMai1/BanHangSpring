package com.learncode.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.learncode.models.Sanpham;
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
	

	@Query(value = "SELECT max(spct.id) id, spct.idsanpham, max(spct.kichthuoc) kichthuoc, max(spct.soluong) soluong, min(spct.giatien) giatien, max(spct.giamgia) giamgia, max(spct.isdelete) isdelete FROM qtht_sanphamvachitiet spct \r\n" + 
			"												LEFT JOIN\r\n" + 
			"												(SELECT sp.* FROM qtht_sanpham sp \r\n" + 
			"												GROUP BY sp.id) pssum \r\n" + 
			"												ON pssum.id = spct.idsanpham \r\n" + 
			"												LEFT JOIN qtht_sanphamvachitiet ps\r\n" + 
			"												ON pssum.id = ps.idsanpham \r\n" + 
			"												WHERE pssum.highlight= 0\r\n" + 
			"												GROUP BY spct.idsanpham\r\n" + 
			"												LIMIT 5", nativeQuery = true)
	List<SanphamVaChitiet> getSanphammoi();
	
	@Query(value = "SELECT max(spct.id) id, spct.idsanpham, max(spct.kichthuoc) kichthuoc, max(spct.soluong) soluong, min(spct.giatien) giatien, max(spct.giamgia) giamgia, max(spct.isdelete) isdelete FROM qtht_sanphamvachitiet spct \r\n" + 
			"												LEFT JOIN\r\n" + 
			"												(SELECT sp.* FROM qtht_sanpham sp \r\n" + 
			"												GROUP BY sp.id) pssum \r\n" + 
			"												ON pssum.id = spct.idsanpham \r\n" + 
			"												LEFT JOIN qtht_sanphamvachitiet ps\r\n" + 
			"												ON pssum.id = ps.idsanpham \r\n" + 
			"												WHERE pssum.highlight= 1\r\n" + 
			"												GROUP BY spct.idsanpham\r\n" + 
			"												LIMIT 5", nativeQuery = true)
	List<SanphamVaChitiet> getSanphamnoibat();
	
	@Query(value = "SELECT max(spct.id) id, spct.idsanpham, max(spct.kichthuoc) kichthuoc, max(spct.soluong) soluong, min(spct.giatien) giatien, max(spct.giamgia) giamgia, max(spct.isdelete) isdelete FROM qtht_sanphamvachitiet spct \r\n" + 
			"									LEFT JOIN \r\n" + 
			"									(SELECT sp.id FROM qtht_sanpham sp\r\n" + 
			"									GROUP BY sp.id) pssum \r\n" + 
			"									ON pssum.id = spct.idsanpham  \r\n" + 
			"									LEFT JOIN qtht_sanphamvachitiet ps\r\n" + 
			"									ON pssum.id = ps.idsanpham \r\n" + 
			"									GROUP BY spct.idsanpham", nativeQuery = true)
	List<SanphamVaChitiet> getTatcasanpham();
	
	@Query(value = "SELECT max(spct.id) id, spct.idsanpham, max(spct.kichthuoc) kichthuoc, max(spct.soluong) soluong, min(spct.giatien) giatien, max(spct.giamgia) giamgia, max(spct.isdelete) isdelete FROM qtht_sanphamvachitiet spct \r\n" + 
			"												LEFT JOIN\r\n" + 
			"												(SELECT sp.* FROM qtht_sanpham sp \r\n" + 
			"												GROUP BY sp.id) pssum \r\n" + 
			"												ON pssum.id = spct.idsanpham \r\n" + 
			"												LEFT JOIN qtht_sanphamvachitiet ps\r\n" + 
			"												ON pssum.id = ps.idsanpham \r\n" + 
			"												WHERE pssum.highlight= 0\r\n" + 
			"												GROUP BY spct.idsanpham", nativeQuery = true)
	List<SanphamVaChitiet> getAllSanphammoi();
	
	@Query(value = "SELECT max(spct.id) id, spct.idsanpham, max(spct.kichthuoc) kichthuoc, max(spct.soluong) soluong, min(spct.giatien) giatien, max(spct.giamgia) giamgia, max(spct.isdelete) isdelete FROM qtht_sanphamvachitiet spct \r\n" + 
			"												LEFT JOIN\r\n" + 
			"												(SELECT sp.* FROM qtht_sanpham sp \r\n" + 
			"												GROUP BY sp.id) pssum \r\n" + 
			"												ON pssum.id = spct.idsanpham \r\n" + 
			"												LEFT JOIN qtht_sanphamvachitiet ps\r\n" + 
			"												ON pssum.id = ps.idsanpham \r\n" + 
			"												WHERE pssum.highlight= 1\r\n" + 
			"												GROUP BY spct.idsanpham", nativeQuery = true)
	List<SanphamVaChitiet> getAllSanphamnoibat();
	
	@Query(value = "SELECT max(spct.id) id, spct.idsanpham, max(spct.kichthuoc) kichthuoc, max(spct.soluong) soluong, min(spct.giatien) giatien, max(spct.giamgia) giamgia, max(spct.isdelete) isdelete FROM qtht_sanphamvachitiet spct \r\n" + 
			"												LEFT JOIN\r\n" + 
			"												(SELECT sp.* FROM qtht_sanpham sp \r\n" + 
			"												GROUP BY sp.id) pssum \r\n" + 
			"												ON pssum.id = spct.idsanpham \r\n" + 
			"												LEFT JOIN qtht_sanphamvachitiet ps\r\n" + 
			"												ON pssum.id = ps.idsanpham \r\n" + 
			"												WHERE pssum.highlight= 2\r\n" + 
			"												GROUP BY spct.idsanpham", nativeQuery = true)
	List<SanphamVaChitiet> getAllSanphamsale();
	
	@Query(value = "SELECT max(spct.id) id, spct.idsanpham, max(spct.kichthuoc) kichthuoc, max(spct.soluong) soluong, min(spct.giatien) giatien, max(spct.giamgia) giamgia, max(spct.isdelete) isdelete FROM qtht_sanphamvachitiet spct \r\n" + 
			"												LEFT JOIN\r\n" + 
			"												(SELECT sp.* FROM qtht_sanpham sp \r\n" + 
			"												GROUP BY sp.id) pssum \r\n" + 
			"												ON pssum.id = spct.idsanpham \r\n" + 
			"												LEFT JOIN qtht_sanphamvachitiet ps\r\n" + 
			"												ON pssum.id = ps.idsanpham \r\n" + 
			"												WHERE pssum.highlight= 3\r\n" + 
			"												GROUP BY spct.idsanpham", nativeQuery = true)
	List<SanphamVaChitiet> getAllSanphambanchay();
	
	@Query(value = "SELECT max(spct.id) id, spct.idsanpham, max(spct.kichthuoc) kichthuoc, max(spct.soluong) soluong, min(spct.giatien) giatien, max(spct.giamgia) giamgia, max(spct.isdelete) isdelete FROM qtht_sanphamvachitiet spct \r\n" + 
			"												LEFT JOIN\r\n" + 
			"												(SELECT sp.* FROM qtht_sanpham sp \r\n" + 
			"												GROUP BY sp.id) pssum \r\n" + 
			"												ON pssum.id = spct.idsanpham \r\n" + 
			"												LEFT JOIN qtht_sanphamvachitiet ps\r\n" + 
			"												ON pssum.id = ps.idsanpham \r\n" + 
			"												WHERE spct.kichthuoc=?\r\n" + 
			"												GROUP BY spct.idsanpham", nativeQuery = true)
	List<SanphamVaChitiet> getSearchSize(@Param("size") String size);
	
	@Query(value = "SELECT max(spct.id) id, spct.idsanpham, max(spct.kichthuoc) kichthuoc, max(spct.soluong) soluong, min(spct.giatien) giatien, max(spct.giamgia) giamgia, max(spct.isdelete) isdelete FROM qtht_sanphamvachitiet spct \r\n" + 
			"												LEFT JOIN\r\n" + 
			"												(SELECT sp.* FROM qtht_sanpham sp \r\n" + 
			"												GROUP BY sp.id) pssum \r\n" + 
			"												ON pssum.id = spct.idsanpham \r\n" + 
			"												LEFT JOIN qtht_sanphamvachitiet ps\r\n" + 
			"												ON pssum.id = ps.idsanpham \r\n" + 
			"												WHERE spct.giatien BETWEEN ? AND ?\r\n" + 
			"												GROUP BY spct.idsanpham\r\n" + 
			"												", nativeQuery = true)
	List<SanphamVaChitiet> searchGiatien(@Param("min") float min, @Param("max") float max);
	
	@Query(value = "SELECT * FROM qtht_sanphamvachitiet WHERE kichthuoc @@ to_tsquery(?) AND isdelete = 0", nativeQuery = true)
	List<SanphamVaChitiet> searchKichThuoc(@Param("kichthuoc") String kichthuoc);
}
