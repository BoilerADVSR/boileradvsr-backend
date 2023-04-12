package com.boileradvsr.backend.models.repositories;

import com.boileradvsr.backend.models.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo, String> {
}
