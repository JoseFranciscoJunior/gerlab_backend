package com.josefrancisco.helpdesk.api.service.impl;

import com.josefrancisco.helpdesk.api.repository.ScheduleRepository;
import com.josefrancisco.helpdesk.api.security.entity.Schedule;
import com.josefrancisco.helpdesk.api.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;

	public Schedule findByEmail(String email) {
		return this.scheduleRepository.findByEmail(email);
	}

	public Schedule createOrUpdate(Schedule schedule) {
		return this.scheduleRepository.save(schedule);
	}

	public Schedule findById(String id) {
		return this.scheduleRepository.findOne(id);
	}

	public void delete(String id) {
		this.scheduleRepository.delete(id);
	}

	public Page<Schedule> findAll(int page, int count) {
		Pageable pages = new PageRequest(page, count);
		return this.scheduleRepository.findAll(pages);
	}
}
