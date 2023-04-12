package com.boileradvsr.backend.models.repositories;

import com.boileradvsr.backend.models.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface ChatRepository extends MongoRepository<Chat, String> {
    public Chat findChatByNamesContaining(ArrayList<String> names);
}
