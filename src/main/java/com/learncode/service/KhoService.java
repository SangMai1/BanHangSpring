package com.learncode.service;

import java.util.List;
import java.util.Optional;

import com.learncode.models.Kho;

public interface KhoService {

	List<Kho> findAll();

	void update(Kho k);

	void save(Kho k);

	Optional<Kho> findById(Long id);

	List<Kho> listSearchSize(int trangthai);

}
