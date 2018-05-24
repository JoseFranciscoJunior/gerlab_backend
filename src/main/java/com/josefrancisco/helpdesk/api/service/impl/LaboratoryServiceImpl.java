package com.josefrancisco.helpdesk.api.service.impl;

import com.josefrancisco.helpdesk.api.repository.LaboratoryRepository;
import com.josefrancisco.helpdesk.api.security.entity.Laboratory;
import com.josefrancisco.helpdesk.api.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class LaboratoryServiceImpl implements LaboratoryService {

	@Autowired
	private LaboratoryRepository laboratoryRepository;

	public Laboratory findByEmail(String email) {
		return this.laboratoryRepository.findByEmail(email);
	}

	public Laboratory createOrUpdate(Laboratory laboratory) {
		return this.laboratoryRepository.save(laboratory);
	}

	public Laboratory findById(String id) {
		return this.laboratoryRepository.findOne(id);
	}

	public void delete(String id) {
		this.laboratoryRepository.delete(id);
	}

	public Page<Laboratory> findAll(int page, int count) {
		Pageable pages = new PageRequest(page, count);
		return this.laboratoryRepository.findAll(pages);
	}
}
