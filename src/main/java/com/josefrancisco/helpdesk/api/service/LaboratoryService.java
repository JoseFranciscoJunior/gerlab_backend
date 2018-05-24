package com.josefrancisco.helpdesk.api.service;

import com.josefrancisco.helpdesk.api.security.entity.Laboratory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public interface LaboratoryService {

	Laboratory findByEmail(String email);
	
	Laboratory createOrUpdate(Laboratory laboratory);
	
	Laboratory findById(String id);
	
	void delete(String id);
	
	Page<Laboratory> findAll(int page, int count);
}
