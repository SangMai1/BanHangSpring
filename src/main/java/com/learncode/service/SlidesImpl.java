package com.learncode.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learncode.models.Slides;
import com.learncode.repository.SlidesRepository;


@Service
public class SlidesImpl implements SlidesService {

	@Autowired
	SlidesRepository slidesRepository;

	@Override
	public void insertSlides(Slides slides) {
		this.slidesRepository.save(slides);
		//slides.setId(ThreadLocalRandom.current().nextLong(0, new Long("9000000000000000000")));
		//this.slidesRepository.insertSlides(slides.getId(), slides.getMaslides(), slides.getTenslides(), slides.getImages(), slides.getCreateby(), slides.getCreateday(), slides.getUpdateby(), slides.getUpdateday(), slides.getIsdelete());
	}
	
	

	@Override
	public Optional<Slides> findSlidesById(Long id) {
		return this.slidesRepository.findSlidesById(id);
	}



	@Override
	public void updateSlides(Slides sl) {
		this.slidesRepository.save(sl);
		//return this.slidesRepository.updateSlides(sl.getMaslides(), sl.getTenslides(), sl.getImages(), sl.getUpdateby(), sl.getUpdateday(), sl.getIsdelete(), sl.getId());
	}



	@Override
	public List<Slides> getAll() {
		return this.slidesRepository.getAll();
	}



	@Override
	public List<Slides> searchTenSlides(String tenslides) {
		return slidesRepository.searchTenSlides(tenslides);
	}



	@Override
	public void deleteSlides(Slides sl) {
		slidesRepository.deleteSlides(sl.getUpdateby(), sl.getUpdateday(), sl.getIsdelete(), sl.getId());
	}




	
	
}
