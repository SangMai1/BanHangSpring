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

@Repository
@Transactional
public interface SanphamRepository extends CrudRepository<Sanpham, Long> {

	@Modifying
	@Query(value = "INSERT INTO public.qtht_sanpham(id, masanpham, tensanpham, image, createday, createby, updateday, updateby, xuatxu, mota, isdelete, maloaisanpham) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", nativeQuery = true)
	void insertSanpham(@Param("id") Long id, @Param("masanpham") String masanpham, @Param("tensanpham") String tensanpham, @Param("image") String image, @Param("createday") Date createday, @Param("createby") String createby, @Param("updateday") Date updateday, @Param("updateby") String updateby, @Param("xuatxu") String xuatxu, @Param("mota") String mota, @Param("isdelete") Integer isdelete, @Param("maloaisanpham") Long maloaisanpham);
	
	@Modifying
	@Query(value = "UPDATE public.qtht_sanpham SET masanpham=?, tensanpham=?, image=?, updateday=?, updateby=?, xuatxu=?, mota=?, isdelete=?, maloaisanpham=? WHERE id = ?;", nativeQuery = true)
	int updateSanpham(@Param("masanpham") String masanpham, @Param("tensanpham") String tensanpham, @Param("image") String image, @Param("updateday") Date updateday, @Param("updateby") String updateby, @Param("xuatxu") String xuatxu, @Param("mota") String mota, @Param("isdelete") Integer isdelete, @Param("maloaisanpham") Long maloaisanpham, @Param("id") Long id);
	
	@Query(value = "SELECT id, masanpham, tensanpham, image, createday, createby, updateday, updateby, xuatxu, mota, isdelete, maloaisanpham FROM qtht_sanpham WHERE id = ?", nativeQuery = true)
	Optional<Sanpham> finBySanphamId(@Param("id") Long id);
	

	@Query(value = "SELECT id, masanpham, tensanpham, image, createday, createby, updateday, updateby, xuatxu, mota, isdelete, maloaisanpham FROM qtht_sanpham WHERE isdelete = 0", nativeQuery = true)
	List<Sanpham> getAllSanpham();
}
