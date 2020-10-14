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

import groovyjarjarpicocli.CommandLine.Parameters;

@Repository
@Transactional
public interface VaiTroRepository extends CrudRepository<VaiTro, Long>{
	
	@Modifying
	@Query(value = "INSERT INTO public.qtht_vaitro(id, mavaitro, tenvaitro, nguoitao, createday, nguoiupdate, updateday, isdelete) VALUES (?, ?, ?, ?, ?, ?, ?, ?);", nativeQuery = true)
	void insertVaitro(@Param("id") Long id, @Param("mavaitro") String mavaitro, @Param("tenvaitro") String tenvaitro, @Param("nguoitao") String nguoitao, @Param("createday") Date createday, @Param("nguoiupdate") String nguoiupdate, @Param("updateday") Date updateday, @Param("isdelete") Integer isdelete);
	
	@Modifying
	@Query(value = "INSERT INTO public.qtht_vaitrovachucnang(idvaitro, idchucnang) VALUES (?, ?);", nativeQuery = true)
	void insertVaitroVaChucnang(@Param("idvaitro") Long idvaitro, @Param("idchucnang") Long idchucnang);
	
	@Modifying
	@Query(value = "UPDATE public.qtht_vaitro SET mavaitro=?, tenvaitro=?, nguoiupdate=?, updateday=?, isdelete=? WHERE id=?;", nativeQuery = true)
	void updateVaitro(@Param("mavaitro") String mavaitro, @Param("tenvaitro") String tenvaitro, @Param("nguoiupdate") String nguoiupdate, @Param("updateday") Date updateday, @Param("isdelete") Integer isdelete, @Param("id") Long id);
	
	@Modifying
	@Query(value = "DELETE FROM public.qtht_vaitrovachucnang WHERE idvaitro=?;", nativeQuery = true)
	int deleteVaitroVaChucnang(@Param("idvaitro") Long idvaitro);
	
	@Query(value = "SELECT id, mavaitro ,tenvaitro, nguoitao, createday, nguoiupdate, updateday, isdelete FROM qtht_vaitro where id = ?", nativeQuery = true)
	Optional<VaiTro> findByVaitroId(Long id);
	
	@Query(value = "SELECT id, mavaitro, tenvaitro, nguoitao, createday, nguoiupdate, updateday, isdelete FROM qtht_vaitro where isdelete = 0", nativeQuery = true)
	List<VaiTro> listVaiTro();
	
	@Query(value="SELECT * FROM qtht_vaitro WHERE tenvaitro @@ to_tsquery(?1) and isdelete = 0", nativeQuery = true)
	List<VaiTro> findByTenvaitro(String tenvaitro);
	
	@Query(value="SELECT * FROM qtht_vaitro WHERE mavaitro @@ to_tsquery(?1) and isdelete = 0", nativeQuery = true)
	List<VaiTro> findByMavaitro(String mavaitro);
	
	@Query(value = "SELECT idchucnang from qtht_vaitrovachucnang where idvaitro=?", nativeQuery = true)
	List<Long> findChucnangVaitro(@Param("idvaitro") Long idvaitro);
	

}
