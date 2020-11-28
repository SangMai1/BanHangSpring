package com.learncode.service;

import java.util.List;
import java.util.Optional;

import com.learncode.models.Slides;

public interface SlidesService {

	void insertSlides(Slides slides);

	List<Slides> getAll();

	Optional<Slides> findSlidesById(Long id);

	void updateSlides(Slides sl);

	void deleteSlides(Slides sl);

	List<Slides> searchTenSlides(String tenslides);



}
