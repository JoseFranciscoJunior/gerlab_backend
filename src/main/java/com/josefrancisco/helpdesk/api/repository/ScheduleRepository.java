package com.josefrancisco.helpdesk.api.repository;

import com.josefrancisco.helpdesk.api.security.entity.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {
}
