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

import com.learncode.models.LoaiSanPham;

@Repository
public interface LoaisanphamRepository extends CrudRepository<LoaiSanPham, Long>{
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO public.qtht_loaisanpham(id, maloaisanpham, tenloaisanpham, createday, createby, updateday, updateby, isdelete) VALUES (?, ?, ?, ?, ?, ?, ?, ?);", nativeQuery = true)
	void insertLoaisanpham(@Param("id") Long id, @Param("maloaisanpham") String maloaisanpham, @Param("tenloaisanpham") String tenloaisanpham, @Param("createday") Date createday, @Param("createby") String createby, @Param("updateday") Date updateday, @Param("updateby") String updateby, @Param("isdelete") Integer isdelete);

	@Transactional
	@Query(value = "SELECT id, maloaisanpham, tenloaisanpham, createday, createby, updateday, updateby, isdelete FROM qtht_loaisanpham WHERE isdelete = 0", nativeQuery = true)
	List<LoaiSanPham> findAllLoaisanpham();
	
	@Query(value = "SELECT id, maloaisanpham, tenloaisanpham, createday, createby, updateday, updateby, isdelete FROM qtht_loaisanpham WHERE id = ? AND isdelete = 0", nativeQuery = true)
	Optional<LoaiSanPham> findLoaisanphamById(@Param("id") Long id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE public.qtht_loaisanpham SET maloaisanpham=?, tenloaisanpham=?, updateday=?, updateby=?, isdelete=? WHERE id = ?;", nativeQuery = true)
	void updateLoaisanpham(@Param("maloaisanpham") String maloaisanpham, @Param("tenloaisanpham") String tenloaisanpham, @Param("updateday") Date updateday, @Param("updateby") String updateby, @Param("isdelete") Integer isdelete, @Param("id") Long id);
}
