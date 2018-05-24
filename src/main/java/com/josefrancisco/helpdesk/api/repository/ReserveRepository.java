package com.josefrancisco.helpdesk.api.repository;

import com.josefrancisco.helpdesk.api.security.entity.Reserve;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReserveRepository extends MongoRepository<Reserve, String> {
}
