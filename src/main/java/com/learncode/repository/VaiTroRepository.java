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

import com.learncode.models.VaiTro;

@Repository
public interface VaiTroRepository extends CrudRepository<VaiTro, Long>{
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO public.qtht_vaitro(id, mavaitro, tenvaitro, nguoitao, createday, nguoiupdate, updateday, isdelete) VALUES (?, ?, ?, ?, ?, ?, ?, ?);", nativeQuery = true)
	int insertVaitro(@Param("id") Long id, @Param("mavaitro") String mavaitro, @Param("tenvaitro") String tenvaitro, @Param("nguoitao") String nguoitao, @Param("createday") Date createday, @Param("nguoiupdate") String nguoiupdate, @Param("updateday") Date updateday, @Param("isdelete") Integer isdelete);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE public.qtht_vaitro SET mavaitro=?, tenvaitro=?, nguoiupdate=?, updateday=?, isdelete=? WHERE id=?;", nativeQuery = true)
	int updateVaitro(@Param("mavaitro") String mavaitro, @Param("tenvaitro") String tenvaitro, @Param("nguoiupdate") String nguoiupdate, @Param("updateday") Date updateday, @Param("isdelete") Integer isdelete, @Param("id") Long id);
	
	@Transactional
	@Query(value = "SELECT id, mavaitro ,tenvaitro, nguoitao, createday, nguoiupdate, updateday, isdelete FROM qtht_vaitro where id = ?", nativeQuery = true)
	Optional<VaiTro> findByVaitroId(Long id);
	
	@Transactional
	@Query(value = "SELECT id, mavaitro, tenvaitro, nguoitao, createday, nguoiupdate, updateday, isdelete FROM qtht_vaitro where isdelete = 0", nativeQuery = true)
	List<VaiTro> listVaiTro();
	
	@Transactional
	@Query(value="SELECT * FROM qtht_vaitro WHERE tenvaitro @@ to_tsquery(?1) and isdelete = 0", nativeQuery = true)
	List<VaiTro> findByTenvaitro(String tenvaitro);
	
	@Transactional
	@Query(value="SELECT * FROM qtht_vaitro WHERE mavaitro @@ to_tsquery(?1) and isdelete = 0", nativeQuery = true)
	List<VaiTro> findByMavaitro(String mavaitro);
}
