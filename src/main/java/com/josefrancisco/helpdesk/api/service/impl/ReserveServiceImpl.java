package com.josefrancisco.helpdesk.api.service.impl;

import com.josefrancisco.helpdesk.api.repository.ReserveRepository;
import com.josefrancisco.helpdesk.api.security.entity.Reserve;
import com.josefrancisco.helpdesk.api.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ReserveServiceImpl implements ReserveService {

	@Autowired
	private ReserveRepository reserveRepository;

	public Reserve findByEmail(String email) {
		return this.reserveRepository.findByEmail(email);
	}

	public Reserve createOrUpdate(Reserve reserve) {
		return this.reserveRepository.save(reserve);
	}

	public Reserve findById(String id) {
		return this.reserveRepository.findOne(id);
	}

	public void delete(String id) {
		this.reserveRepository.delete(id);
	}

	public Page<Reserve> findAll(int page, int count) {
		Pageable pages = new PageRequest(page, count);
		return this.reserveRepository.findAll(pages);
	}
}
