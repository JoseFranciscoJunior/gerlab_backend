package com.josefrancisco.helpdesk.api.service;

import com.josefrancisco.helpdesk.api.security.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public interface ScheduleService {

	Schedule findByEmail(String email);
	
	Schedule createOrUpdate(Schedule schedule);
	
	Schedule findById(String id);
	
	void delete(String id);
	
	Page<Schedule> findAll(int page, int count);
}
