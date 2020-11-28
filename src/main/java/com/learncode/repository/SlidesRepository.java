package com.learncode.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learncode.models.Slides;

@Repository
@Transactional
public interface SlidesRepository extends CrudRepository<Slides, Long> {

	@Modifying
	@Query(value = "INSERT INTO public.ql_slides(id, maslides, tenslides, images, createby, createday, updateby, updateday, isdelete) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);", nativeQuery = true)
	void insertSlides(@Param("id") Long id, @Param("maslides") String maslides, @Param("tenslides") String tenslides, @Param("images") String images, @Param("createby") String createby, @Param("createday") Date createday, @Param("updateby") String updateby, @Param("updateday") Date updateday, @Param("isdelete") Integer isdelete);
	
	@Query(value = "SELECT id, maslides, tenslides, images, createby, createday, updateby, updateday, isdelete FROM ql_slides WHERE id = ?", nativeQuery = true)
	Optional<Slides> findSlidesById(@Param("id") Long id);
	
	@Modifying
	@Query(value = "UPDATE public.ql_slides SET updateby=?, updateday=?, isdelete=? WHERE id=?;", nativeQuery = true)
	void deleteSlides(@Param("updateby") String updateby, @Param("updateday") Date updateday, @Param("isdelete") Integer isdelete, @Param("id") Long id);
	
	@Query(value = "SELECT *\r\n" + 
			"						FROM ql_slides sl\r\n" + 
			"						WHERE isdelete = 0 AND sl.createday < now()  \r\n" + 
			"						ORDER BY sl.createday DESC", nativeQuery = true)
	List<Slides> getAll();
	
	@Query(value = "SELECT * FROM ql_slides WHERE tenslides @@ to_tsquery(?) and isdelete = 0", nativeQuery = true)
	List<Slides> searchTenSlides(String tenslides);
}
