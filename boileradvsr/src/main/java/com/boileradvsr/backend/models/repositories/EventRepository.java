package com.boileradvsr.backend.models.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.boileradvsr.backend.models.*;

public interface EventRepository extends MongoRepository<Event, String> {
}
