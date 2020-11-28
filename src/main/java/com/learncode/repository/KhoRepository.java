package com.learncode.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learncode.models.BillDetail;
import com.learncode.models.Kho;

@Repository
@Transactional
public interface KhoRepository extends JpaRepository<Kho, Long> {

	@Modifying
	@Query(value = "UPDATE public.ql_kho SET trangthai=? WHERE id=?;", nativeQuery = true)
	void updateKho(@Param("trangthai") int trangthai, @Param("id") long id);
	
	@Query(value = "SELECT * FROM ql_kho WHERE trangthai = ?", nativeQuery = true)
	List<Kho> listSearchSize(@Param("trangthai") int trangthai);
}
