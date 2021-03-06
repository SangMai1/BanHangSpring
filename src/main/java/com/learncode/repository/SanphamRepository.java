package com.learncode.repository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.learncode.models.Sanpham;
import com.learncode.models.SanphamVaChitiet;

@Repository
@Transactional
public interface SanphamRepository extends CrudRepository<Sanpham, Long> {

//	@Modifying
//	@Query(value = "INSERT INTO public.qtht_sanpham(id, masanpham, tensanpham, image, createday, createby, updateday, updateby, xuatxu, mota, isdelete, maloaisanpham) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", nativeQuery = true)
//	void insertSanpham( Long id, @Param("masanpham") String masanpham, @Param("tensanpham") String tensanpham, @Param("image") String image, @Param("createday") Date createday, @Param("createby") String createby, @Param("updateday") Date updateday, @Param("updateby") String updateby, @Param("xuatxu") String xuatxu, @Param("mota") String mota, @Param("isdelete") Integer isdelete, @Param("maloaisanpham") Long maloaisanpham);
	
	@Modifying
	@Query(value = "UPDATE public.qtht_sanpham SET updateday=?, nguoiupdate=?, isdelete=? WHERE id = ?;", nativeQuery = true)
	void deleteSanpham(@Param("updateday") Date updateday, @Param("nguoiupdate") String nguoiupdate, @Param("isdelete") Integer isdelete, @Param("id") Long id);
	
	@Query(value = "SELECT id, masanpham, tensanpham, image, createday, nguoitao, updateday, nguoiupdate, xuatxu, mota, isdelete, maloaisanpham, highlight FROM qtht_sanpham WHERE id = ?", nativeQuery = true)
	Optional<Sanpham> finBySanphamId(@Param("id") Long id);
	
	@Query(value ="SELECT sp.id, sp.masanpham, sp.tensanpham, sp.image, sp.createday, sp.nguoitao, sp.updateday, sp.nguoiupdate, sp.xuatxu, sp.mota, sp.maloaisanpham, sp.isdelete, spct.kichthuoc, spct.giatien\r\n" + 
			"FROM qtht_sanpham sp \r\n" + 
			"INNER JOIN qtht_sanphamvachitiet spct ON spct.idsanpham = sp.id\r\n" + 
			"WHERE sp.isdelete = 0 and spct.isdelete = 0", nativeQuery = true)
	List<Sanpham> getSanphamAndSanphamchitiet();

	@Query(value = "SELECT * \r\n" + 
			"FROM qtht_sanpham sp\r\n" + 
			"WHERE isdelete = 0 AND sp.createday < now()\r\n" + 
			"ORDER BY sp.updateday DESC ", nativeQuery = true)
	List<Sanpham> getAllSanpham();
	
	@Query(value = "SELECT sp.*, min(ps.giatien) FROM qtht_sanpham sp\r\n" + 
			"LEFT JOIN \r\n" + 
			"(SELECT spct.id, spct.idsanpham FROM qtht_sanphamvachitiet spct\r\n" + 
			"GROUP BY spct.id, spct.idsanpham) pssum\r\n" + 
			"ON pssum.idsanpham = sp.id\r\n" + 
			"LEFT JOIN qtht_sanphamvachitiet ps\r\n" + 
			"ON ps.idsanpham = pssum.idsanpham\r\n" + 
			"AND pssum.id = ps.id\r\n" + 
			"GROUP BY sp.id\r\n" + 
			"LIMIT 5", nativeQuery = true)
	List<Sanpham> getSanphammoi();
	
	@Query(value = "SELECT sp.id, sp.masanpham, sp.tensanpham, sp.image, sp.maloaisanpham, sp.nguoitao, sp.createday, sp.updateday, sp.nguoiupdate, sp.xuatxu, sp.mota, sp.isdelete, sp.highlight FROM qtht_sanpham sp\r\n" + 
			"INNER JOIN qtht_sanphamvachitiet spvct ON spvct.idsanpham = sp.id\r\n" + 
			"WHERE sp.isdelete = 0 AND spvct.isdelete = 0 AND spvct.giatien BETWEEN ? AND ?\r\n" + 
			"GROUP BY sp.id", nativeQuery = true)
	List<Sanpham> searchGiatien(@Param("min") float min, @Param("max") float max);
	
	@Query(value = "SELECT sp.id, sp.masanpham, sp.tensanpham, sp.image, sp.maloaisanpham, sp.nguoitao, sp.createday, sp.updateday, sp.nguoiupdate, sp.xuatxu, sp.mota, sp.isdelete, sp.highlight FROM qtht_sanpham sp\r\n" + 
			"			INNER JOIN qtht_sanphamvachitiet spvct ON spvct.idsanpham = sp.id\r\n" + 
			"			WHERE sp.isdelete = 0 AND spvct.isdelete = 0 AND spvct.kichthuoc = ?\r\n" + 
			"			GROUP BY sp.id", nativeQuery = true)
	List<Sanpham> searchSize(@Param("size") String size);
	

	@Query(value = "SELECT * FROM qtht_sanpham WHERE tensanpham @@ to_tsquery(?) and isdelete = 0", nativeQuery = true)
	List<Sanpham> searchTenSanPham(String tensanpham);
}
