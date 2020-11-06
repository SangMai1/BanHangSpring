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
	int insertSlides(@Param("id") Long id, @Param("maslides") String maslides, @Param("tenslides") String tenslides, @Param("images") String images, @Param("createby") String createby, @Param("createday") Date createday, @Param("updateby") String updateby, @Param("updateday") Date updateday, @Param("isdelete") Integer isdelete);
	
	@Query(value = "SELECT id, maslides, tenslides, images, createby, createday, updateby, updateday, isdelete FROM ql_slides WHERE id = ?", nativeQuery = true)
	Optional<Slides> findSlidesById(@Param("id") Long id);
	
	@Modifying
	@Query(value = "UPDATE public.ql_slides SET maslides=?, tenslides=?, images=?, updateby=?, updateday=?, isdelete=? WHERE id=?;", nativeQuery = true)
	int updateSlides(@Param("maslides") String maslides, @Param("tenslides") String tenslides, @Param("images") String images, @Param("updateby") String updateby, @Param("updateday") Date updateday, @Param("isdelete") Integer isdelete, @Param("id") Long id);
	
	@Query(value = "SELECT * FROM ql_slides WHERE isdelete = 0", nativeQuery = true)
	List<Slides> getAll();
}
