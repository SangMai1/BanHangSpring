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
public interface NguoiDungRepository extends CrudRepository<Nguoidung, Long>{
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO public.qtht_nguoidung(id, manguoidung, tennguoidung, password, email, gender, phone, createday, nguoitao, updateday, nguoiupdate, isdelete) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", nativeQuery = true)
	void insertNguoidung(@Param("id") Long id, @Param("manguoidung") String manguoidung, @Param("tennguoidung") String tennguoidung, @Param("password") String password, @Param("email") String email,@Param("gender") Integer gender, @Param("phone") String phone, @Param("createday") Date createday, @Param("nguoitao") String nguoitao, @Param("updateday") Date updateday, @Param("nguoiupdate") String nguoiupdate, @Param("isdelete") Integer isdelete);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO public.qtht_nguoidungvachucnang(idnguoidung, idchucnang) VALUES (?, ?);", nativeQuery = true)
	void insertNguoidungVaChucnang(@Param("idnguoidung") Long idnguoidung, @Param("idchucnang") Long idchucnang);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO public.qtht_nguoidungvanhomnguoidung(idnguoidung, idnhom) VALUES (?, ?);", nativeQuery = true)
	void insertNguoidungVaNhomnguoidung(@Param("idnguoidung") Long idnguoidung, @Param("idnhom") Long idnhom);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO public.qtht_nguoidungvavaitro(idnguoidung, idvaitro) VALUES (?, ?);", nativeQuery = true)
	void insertNguoidungVaVaitro(@Param("idnguoidung") Long idnguoidung, @Param("idvaitro") Long idvaitro);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE public.qtht_nguoidung SET manguoidung=?, tennguoidung=?, password=?, email=?, gender=?, phone=?, updateday=?, nguoiupdate=?, isdelete=? WHERE id = ?;", nativeQuery = true)
	void updateNguoidung(@Param("manguoidung") String manguoidung, @Param("tennguoidung") String tennguoidung, @Param("password") String password, @Param("email") String email, @Param("gender") Integer gender, @Param("phone") String phone, @Param("updateday") Date updateday, @Param("nguoiupdate") String nguoiupdate, @Param("isdelete") Integer isdelete, @Param("id") Long id);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM public.qtht_nguoidungvachucnang WHERE idnguoidung = ?;", nativeQuery = true)
	void deleteNguoidungVaChucnang(@Param("idnguoidung") Long idnguoidung);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM public.qtht_nguoidungvanhomnguoidung WHERE idnguoidung = ?;", nativeQuery = true)
	void deleteNguoidungVaNhomnguoidung(@Param("idnguoidung") Long idnguoidung);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM public.qtht_nguoidungvavaitro WHERE idnguoidung = ?;", nativeQuery = true)
	void deleteNguoidungVaVaitro(@Param("idnguoidung") Long idnguoidung);
	
	@Transactional
	@Query(value = "SELECT id, manguoidung, tennguoidung, password, email, gender, phone, createday, nguoitao, updateday, nguoiupdate, isdelete from qtht_nguoidung where isdelete = 0", nativeQuery = true)
	List<Nguoidung> getAllNguoiDung();
	
	@Transactional
	@Query(value = "SELECT id, manguoidung, tennguoidung, password, email, gender, phone, createday, nguoitao, updateday, nguoiupdate, isdelete FROM qtht_nguoidung WHERE isdelete = 0 AND id = ?", nativeQuery = true)
	Optional<Nguoidung> findNguoidungById(Long id);
	
	@Transactional
	@Query(value = "SELECT id, manguoidung, tennguoidung, password, email, gender, phone, createday, nguoitao, updateday, nguoiupdate, isdelete from qtht_nguoidung where tennguoidung = ?", nativeQuery = true)
	Optional<Nguoidung> findNguoidungByTennguoidung(String tennguoidung);
	
	@Transactional
	@Query(value = "SELECT * from public.qtht_nguoidung WHERE tennguoidung @@ to_tsquery(?1)", nativeQuery = true)
	List<Nguoidung> findByTennguoidung(String tennguoidung);
	
	@Transactional
	@Query(value = "SELECT nd.id, cn.maapi FROM qtht_nguoidung nd INNER JOIN qtht_nguoidungvachucnang ndvcn ON ndvcn.manguoidung = nd.manguoidung INNER JOIN qtht_chucnang cn ON cn.machucnang = ndvcn.machucnang WHERE nd.tennguoidung = ?", nativeQuery = true)
	Optional<Nguoidung> findUrlChucNang(String tennguoidung);
	
	@Transactional
	@Query(value = "SELECT * FROM qtht_nguoidung WHERE tennguoidung = ?", nativeQuery = true)
	Nguoidung findByTen(String tennguoidung);
	
	@Transactional
	@Query(value = "SELECT * FROM qtht_nguoidung WHERE id = ?", nativeQuery = true)
	Nguoidung findById1(@Param("id") Long id);
	
	@Transactional
	@Query(value = "SELECT idchucnang FROM qtht_nguoidungvachucnang WHERE idnguoidung = ?", nativeQuery = true)
	List<Long> findByIdchucnang(@Param("idnguoidung") Long idnguoidung);
	
	@Transactional
	@Query(value = "SELECT idnhom FROM qtht_nguoidungvanhomnguoidung WHERE idnguoidung = ?", nativeQuery = true)
	List<Long> findByIdnhom(@Param("idnguoidung") Long idnguoidung);
	
	@Transactional
	@Query(value = "SELECT idvaitro FROM qtht_nguoidungvavaitro WHERE idnguoidung = ?", nativeQuery = true)
	List<Long> findByIdvaitro(@Param("idnguoidung") Long idnguoidung);
}
