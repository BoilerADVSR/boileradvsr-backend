package com.boileradvsr.backend.controllers;

import com.boileradvsr.backend.models.Chat;
import com.boileradvsr.backend.models.Message;
import com.boileradvsr.backend.models.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@EnableMongoRepositories
@RequestMapping("/chats")
public class ChatController {
    public ChatRepository repository;
    public ChatController(ChatRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public List<Chat> getAllChats() {
        return repository.findAll();
    }

    @GetMapping
    public Chat getChat(@RequestBody ObjectNode objectNode) {
        String student1 = objectNode.get("student1").asText();
        String student2 = objectNode.get("student2").asText();
        ArrayList<String> names = new ArrayList<>();
        names.add(student1);
        names.add(student2);
        return repository.findChatByNamesContaining(names);
    }

    @GetMapping("/{id}")
    public Chat getChat(@PathVariable String id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PutMapping("/addmessage")
    public ResponseEntity<Chat> sendMessage(@RequestBody ObjectNode objectNode) {
        String chatID = objectNode.get("id").asText();
        String text = objectNode.get("text").asText();
        String senderId = objectNode.get("sender").asText();
        Chat chat = repository.findById(chatID).orElseThrow(RuntimeException::new);

        chat.addMessage(new Message(senderId, text));
        return ResponseEntity.ok(chat);
    }


    @PostMapping
    public ResponseEntity createChat(@RequestBody Chat chat) throws URISyntaxException {
        Chat savedChat = repository.save(chat);
        return ResponseEntity.created(new URI("/chats/" + savedChat.getId())).body(savedChat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteChat(@PathVariable String id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
