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

import com.learncode.models.Nguoidung;

@Repository
@Transactional
public interface NguoiDungRepository extends CrudRepository<Nguoidung, Long>{
	
//	@Modifying
//	@Query(value = "INSERT INTO public.qtht_nguoidung(id, manguoidung, tennguoidung, password, email, gender, phone, createday, nguoitao, updateday, nguoiupdate, isdelete) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", nativeQuery = true)
//	void insertNguoidung(@Param("id") Long id, @Param("manguoidung") String manguoidung, @Param("tennguoidung") String tennguoidung, @Param("password") String password, @Param("email") String email,@Param("gender") Integer gender, @Param("phone") String phone, @Param("createday") Date createday, @Param("nguoitao") String nguoitao, @Param("updateday") Date updateday, @Param("nguoiupdate") String nguoiupdate, @Param("isdelete") Integer isdelete);
//	
//	@Modifying
//	@Query(value = "INSERT INTO public.qtht_nguoidungvanhomnguoidung(idnguoidung, idnhom) VALUES (?, ?);", nativeQuery = true)
//	void insertNguoidungVaNhomnguoidung(@Param("idnguoidung") Long idnguoidung, @Param("idnhom") Long idnhom);
//	
//	@Modifying
//	@Query(value = "INSERT INTO public.qtht_nguoidungvavaitro(idnguoidung, idvaitro) VALUES (?, ?);", nativeQuery = true)
//	void insertNguoidungVaVaitro(@Param("idnguoidung") Long idnguoidung, @Param("idvaitro") Long idvaitro);
	
	@Modifying
	@Query(value = "UPDATE public.qtht_nguoidung SET manguoidung=?, tennguoidung=?, password=?, email=?, gender=?, phone=?, updateday=?, nguoiupdate=?, isdelete=? WHERE id = ?;", nativeQuery = true)
	void updateNguoidung(@Param("manguoidung") String manguoidung, @Param("tennguoidung") String tennguoidung, @Param("password") String password, @Param("email") String email, @Param("gender") Integer gender, @Param("phone") String phone, @Param("updateday") Date updateday, @Param("nguoiupdate") String nguoiupdate, @Param("isdelete") Integer isdelete, @Param("id") Long id);
	
	@Modifying
	@Query(value = "DELETE FROM public.qtht_nguoidungvanhomnguoidung WHERE idnguoidung = ?;", nativeQuery = true)
	void deleteNguoidungVaNhomnguoidung(@Param("idnguoidung") Long idnguoidung);
	
	@Modifying
	@Query(value = "DELETE FROM public.qtht_nguoidungvavaitro WHERE idnguoidung = ?;", nativeQuery = true)
	void deleteNguoidungVaVaitro(@Param("idnguoidung") Long idnguoidung);
	
	@Query(value = "SELECT *\r\n" + 
			"			FROM qtht_nguoidung nd\r\n" + 
			"			WHERE isdelete = 0 AND nd.createday < now() \r\n" + 
			"			ORDER BY nd.createday DESC", nativeQuery = true)
	List<Nguoidung> getAllNguoiDung();
	
	@Query(value = "SELECT id, manguoidung, tennguoidung, password, email, gender, phone, createday, nguoitao, updateday, nguoiupdate, isdelete FROM qtht_nguoidung WHERE isdelete = 0 AND id = ?", nativeQuery = true)
	Optional<Nguoidung> findNguoidungById(Long id);
	
	@Query(value = "SELECT id, manguoidung, tennguoidung, password, email, gender, phone, createday, nguoitao, updateday, nguoiupdate, isdelete from qtht_nguoidung where tennguoidung = ?", nativeQuery = true)
	Optional<Nguoidung> findNguoidungByTennguoidung(String tennguoidung);
	
	@Query(value = "SELECT * from public.qtht_nguoidung WHERE tennguoidung @@ to_tsquery(?1)", nativeQuery = true)
	List<Nguoidung> findByTennguoidung(String tennguoidung);
	
	
	@Query(value = "SELECT * FROM qtht_nguoidung WHERE tennguoidung = ?", nativeQuery = true)
	Nguoidung findByTen(String tennguoidung);
	
	@Query(value = "SELECT * FROM qtht_nguoidung WHERE id = ?", nativeQuery = true)
	Nguoidung findById1(@Param("id") Long id);
	
	
	@Query(value = "SELECT idnhom FROM qtht_nguoidungvanhomnguoidung WHERE idnguoidung = ?", nativeQuery = true)
	List<Long> findByIdnhom(@Param("idnguoidung") Long idnguoidung);
	
	@Query(value = "SELECT idvaitro FROM qtht_nguoidungvavaitro WHERE idnguoidung = ?", nativeQuery = true)
	List<Long> findByIdvaitro(@Param("idnguoidung") Long idnguoidung);
	
	@Query(value = "WITH RECURSIVE tree AS \r\n" + 
			"						(SELECT cn.id, cn.machucnang, cn.tenchucnang, cn.maapi, cn.createday, cn.nguoitao, cn.updateday, cn.nguoiupdate, cn.parentid, cn.isdelete, ARRAY[cn.id] as path\r\n" + 
			"						 	FROM qtht_chucnang cn \r\n" + 
			"							WHERE cn.id in\r\n" + 
			"						 		(SELECT DISTINCT CASE WHEN cn.parentid < 0 THEN cn.id ELSE cn.parentid END \r\n" + 
			"								 FROM qtht_chucnang cn\r\n" + 
			"								 	LEFT JOIN qtht_nhomnguoidungchucnang nndvcn ON nndvcn.idchucnang = cn.id \r\n" + 
			"									LEFT JOIN qtht_nhomnguoidung nnd ON nnd.id = nndvcn.idnhom\r\n" + 
			"									LEFT JOIN qtht_nguoidungvanhomnguoidung ndvnnd ON ndvnnd.idnhom = nnd.id\r\n" + 
			"								 	LEFT JOIN qtht_vaitrovachucnang vtvcn ON vtvcn.idchucnang = cn.id \r\n" + 
			"								 	LEFT JOIN qtht_vaitro vt ON vt.id = vtvcn.idvaitro\r\n" + 
			"								 	LEFT JOIN qtht_nguoidungvavaitro ndvvt ON ndvvt.idvaitro = vt.id\r\n" + 
			"									LEFT JOIN qtht_nguoidung nd ON nd.id = ndvvt.idnguoidung OR  nd.id = ndvnnd.idnguoidung  \r\n" + 
			"									WHERE nd.isdelete = 0 AND nnd.isdelete = 0 AND cn.isdelete = 0 AND nd.tennguoidung = ?\r\n" + 
			"								) \r\n" + 
			"						 		UNION ALL\r\n" + 
			"						 		SELECT DISTINCT cn.id, cn.machucnang, cn.tenchucnang, cn.maapi, cn.createday, cn.nguoitao, cn.updateday, cn.nguoiupdate, cn.parentid, cn.isdelete, tr.path || cn.id \r\n" + 
			"						 		FROM qtht_chucnang cn\r\n" + 
			"						 			LEFT JOIN qtht_nhomnguoidungchucnang nndvcn ON nndvcn.idchucnang = cn.id  \r\n" + 
			"									LEFT JOIN qtht_nhomnguoidung nnd ON nnd.id = nndvcn.idnhom \r\n" + 
			"									LEFT JOIN qtht_nguoidungvanhomnguoidung ndvnnd ON ndvnnd.idnhom = nnd.id \r\n" + 
			"						 			LEFT JOIN qtht_vaitrovachucnang vtvcn ON vtvcn.idchucnang = cn.id \r\n" + 
			"						 			LEFT JOIN qtht_vaitro vt ON vt.id = vtvcn.idvaitro\r\n" + 
			"						 			LEFT JOIN qtht_nguoidungvavaitro ndvvt ON ndvvt.idvaitro = vt.id \r\n" + 
			"									LEFT JOIN qtht_nguoidung nd ON nd.id = ndvvt.idnguoidung OR  nd.id = ndvnnd.idnguoidung \r\n" + 
			"						 			INNER JOIN tree tr ON tr.id = cn.parentid AND nd.tennguoidung = ?\r\n" + 
			"						) \r\n" + 
			"						SELECT id, machucnang, tenchucnang, maapi, createday, nguoitao, updateday, nguoiupdate, parentid, isdelete FROM tree \r\n" + 
			"						WHERE isdelete = 0 ORDER BY path", nativeQuery = true)
	Nguoidung findUrl(String tennguoidung, String tennguoidung1);
	
	@Query(value = "SELECT nd.tennguoidung, vt.tenvaitro  FROM qtht_nguoidungvavaitro ndvvt\r\n" + 
			"	LEFT JOIN qtht_nguoidung nd ON nd.id = ndvvt.idnguoidung\r\n" + 
			"	LEFT JOIN qtht_vaitro vt ON vt.id = ndvvt.idvaitro\r\n" + 
			"	WHERE nd.tennguoidung = ?", nativeQuery = true)
	List<String> findUrlNd(String tennguoidung);
	
	@Query(value = "SELECT idvaitro FROM qtht_nguoidungvavaitro WHERE idnguoidung = ?", nativeQuery = true)
	List<String> getRoleName(@Param("idnguoidung") Long idnguoidung);
}
