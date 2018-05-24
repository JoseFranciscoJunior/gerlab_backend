package com.josefrancisco.helpdesk.api.service;

import com.josefrancisco.helpdesk.api.security.entity.Reserve;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public interface ReserveService {

	Reserve findByEmail(String email);
	
	Reserve createOrUpdate(Reserve reserve);
	
	Reserve findById(String id);
	
	void delete(String id);
	
	Page<Reserve> findAll(int page, int count);
}
