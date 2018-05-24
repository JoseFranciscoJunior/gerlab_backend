package com.josefrancisco.helpdesk.api.repository;

import com.josefrancisco.helpdesk.api.security.entity.Laboratory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LaboratoryRepository extends MongoRepository<Laboratory, String> {
}
