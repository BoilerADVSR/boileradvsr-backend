package com.boileradvsr.backend.models;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends MongoRepository<Token, String> {
    List<Token> findAllValidTokenByStudent(String email);

    Optional<Token> findByToken(String token);
}
