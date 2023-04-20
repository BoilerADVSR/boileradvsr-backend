package com.boileradvsr.backend.controllers;

import com.boileradvsr.backend.models.DegreeGraph;
import com.boileradvsr.backend.models.*;
import com.boileradvsr.backend.models.repositories.EventRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@EnableMongoRepositories
@RequestMapping("/events")
public class EventController {
    public EventRepository repository;

    public EventController(EventRepository repository) {this.repository = repository;}

    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return repository.findAll();
    }

    @PostMapping()
    public ResponseEntity createEvent(@RequestBody Event event) {
        Event savedEvent = repository.save(event);
        return ResponseEntity.ok(savedEvent);
    }

}
