package com.learncode.repository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.learncode.models.ChucNang1;

@Repository
@Transactional
public interface ChucNang1Repository extends JpaRepository<ChucNang1, Long>{
	
	@Modifying
	@Query(value = "INSERT INTO public.qtht_chucnang(id, machucnang, tenchucnang, maapi, createday, nguoitao, updateday, nguoiupdate, parentid, isdelete) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", nativeQuery = true)
	int insertChucNang1(@Param("id") Long id, @Param("machucnang") String machucnang, @Param("tenchucnang") String tenchucnang, @Param("maapi") String maapi, @Param("createday") Date createday, @Param("nguoitao") String nguoitao, @Param("updateday") Date updateday, @Param("nguoiupdate") String nguoiupdate,@Param("parentid") Long parentid, @Param("isdelete") Integer isdelete);
	
	
	@Modifying
	@Query(value = "UPDATE public.qtht_chucnang SET machucnang=?, tenchucnang=?, maapi=?, updateday=?, nguoiupdate=? WHERE id=?;", nativeQuery = true)
	int updateChucNang1(@Param("machucnang") String machucnang, @Param("tenchucnang") String tenchucnang, @Param("maapi") String maapi, @Param("updateday") Date updateday, @Param("nguoiupdate") String nguoiupdate, @Param("id") Long id);
	
	@Modifying
	@Query(value = "UPDATE public.qtht_chucnang SET updateday = ?, nguoiupdate=?, isdelete=1 WHERE id=?", nativeQuery = true)
	int deleteChucNang1(@Param("id") Long id);
	
	@Query(value = "SELECT cn.id, cn.machucnang, cn.tenchucnang, cn.maapi, cn.createday, cn.nguoitao, cn.updateday, cn.nguoiupdate, cn.parentid, cn.isdelete FROM qtht_chucnang cn WHERE cn.id = ?;", nativeQuery = true)
	Optional<ChucNang1> findByChucNangEditId(Long id);
	
	@Query(value = "WITH RECURSIVE cte AS \r\n" + 
			"(\r\n" + 
			"	SELECT cn.id, cn.machucnang, cn.tenchucnang, cn.maapi, cn.createday, cn.nguoitao, cn.updateday, cn.nguoiupdate, cn.parentid, cn.isdelete, ARRAY[cn.id] as path\r\n" + 
			"	FROM qtht_chucnang as cn\r\n" + 
			"	WHERE cn.parentid < 0\r\n" + 
			"	UNION ALL\r\n" + 
			"	SELECT cn.id, cn.machucnang, cn.tenchucnang, cn.maapi, cn.createday, cn.nguoitao, cn.updateday, cn.nguoiupdate, cn.parentid, cn.isdelete, ct.path || cn.id\r\n" + 
			"	FROM qtht_chucnang as cn \r\n" + 
			"	INNER JOIN cte as ct ON ct.id = cn.parentid\r\n" + 
			")\r\n" + 
			"SELECT id, machucnang,case when parentid < 0 then concat('--- ', tenchucnang) else tenchucnang end, maapi, createday, nguoitao, updateday, nguoiupdate, parentid, isdelete FROM cte WHERE isdelete = 0 ORDER BY path ", nativeQuery = true)
	List<ChucNang1> findAllChucNang1();
	
	@Query(value = "SELECT id, machucnang, tenchucnang, maapi, createday, nguoitao, updateday, nguoiupdate, parentid, isdelete FROM qtht_chucnang WHERE isdelete = 0", nativeQuery = true)
	List<ChucNang1> getAllChucNang1();
	
	@Query(value = "SELECT id, machucnang, tenchucnang, maapi, createday, nguoitao, updateday, nguoiupdate, parentid, isdelete FROM qtht_chucnang WHERE parentid < 0 and isdelete = 0", nativeQuery = true)
	List<ChucNang1> getAllChucNang1Parent();
	
	@Query(value = "SELECT * FROM qtht_chucnang WHERE tenchucnang @@ to_tsquery(?) and isdelete = 0", nativeQuery = true)
	List<ChucNang1> findByTenchucnang(String tenchucnang);
	
	@Query(value = "SELECT * FROM qtht_chucnang WHERE machucnang @@ to_tsquery(?) and isdelete = 0", nativeQuery = true)
	List<ChucNang1> findByMachucnang(String machucnang);
	
	@Query(value = "SELECT count(*) FROM qtht_chucnang WHERE parentid = ? and isdelete = 0", nativeQuery = true)
	long count(Long id);
	
	@Query(value = "WITH RECURSIVE tree AS\r\n" + 
			"			(SELECT cn.id, cn.machucnang, cn.tenchucnang, cn.maapi, cn.createday, cn.nguoitao, cn.updateday, cn.nguoiupdate, cn.parentid, cn.isdelete, ARRAY[cn.id] as path\r\n" + 
			"			 	FROM qtht_chucnang cn \r\n" + 
			"				WHERE cn.id in\r\n" + 
			"			 		(SELECT DISTINCT CASE WHEN cn.parentid < 0 THEN cn.id ELSE cn.parentid END \r\n" + 
			"					 FROM qtht_chucnang cn\r\n" + 
			"					 	LEFT JOIN qtht_nhomnguoidungchucnang nndvcn ON nndvcn.idchucnang = cn.id \r\n" + 
			"						LEFT JOIN qtht_nhomnguoidung nnd ON nnd.id = nndvcn.idnhom\r\n" + 
			"						LEFT JOIN qtht_nguoidungvanhomnguoidung ndvnnd ON ndvnnd.idnhom = nnd.id\r\n" + 
			"					 	LEFT JOIN qtht_vaitrovachucnang vtvcn ON vtvcn.idchucnang = cn.id\r\n" + 
			"					 	LEFT JOIN qtht_vaitro vt ON vt.id = vtvcn.idvaitro\r\n" + 
			"					 	LEFT JOIN qtht_nguoidungvavaitro ndvvt ON ndvvt.idvaitro = vt.id\r\n" + 
			"						LEFT JOIN qtht_nguoidung nd ON nd.id = ndvvt.idnguoidung OR  nd.id = ndvnnd.idnguoidung  \r\n" + 
			"						WHERE nd.isdelete = 0 AND nnd.isdelete = 0 AND cn.isdelete = 0 AND nd.tennguoidung = ?\r\n" + 
			"					) \r\n" + 
			"			 		UNION ALL\r\n" + 
			"			 		SELECT DISTINCT cn.id, cn.machucnang, cn.tenchucnang, cn.maapi, cn.createday, cn.nguoitao, cn.updateday, cn.nguoiupdate, cn.parentid, cn.isdelete, tr.path || cn.id\r\n" + 
			"			 		FROM qtht_chucnang cn\r\n" + 
			"			 			LEFT JOIN qtht_nhomnguoidungchucnang nndvcn ON nndvcn.idchucnang = cn.id \r\n" + 
			"						LEFT JOIN qtht_nhomnguoidung nnd ON nnd.id = nndvcn.idnhom \r\n" + 
			"						LEFT JOIN qtht_nguoidungvanhomnguoidung ndvnnd ON ndvnnd.idnhom = nnd.id\r\n" + 
			"			 			LEFT JOIN qtht_vaitrovachucnang vtvcn ON vtvcn.idchucnang = cn.id\r\n" + 
			"			 			LEFT JOIN qtht_vaitro vt ON vt.id = vtvcn.idvaitro\r\n" + 
			"			 			LEFT JOIN qtht_nguoidungvavaitro ndvvt ON ndvvt.idvaitro = vt.id\r\n" + 
			"						LEFT JOIN qtht_nguoidung nd ON nd.id = ndvvt.idnguoidung OR  nd.id = ndvnnd.idnguoidung \r\n" + 
			"			 			INNER JOIN tree tr ON tr.id = cn.parentid AND nd.tennguoidung = ?\r\n" + 
			"			) \r\n" + 
			"			SELECT id, machucnang, tenchucnang, maapi, createday, nguoitao, updateday, nguoiupdate, parentid, isdelete FROM tree \r\n" + 
			"			WHERE isdelete = 0 ORDER BY path", nativeQuery = true)
	List<ChucNang1> findChucnangByTennguoidung(String tennguoidung, String tennguoidung1);
	
	@Query(value = "SELECT tenchucnang FROM qtht_chucnang", nativeQuery = true)
	List<String> maapi();
	
	
}
