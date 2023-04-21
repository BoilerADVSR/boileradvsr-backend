package com.boileradvsr.backend.controllers;

import com.boileradvsr.backend.models.DegreeGraph;
import com.boileradvsr.backend.models.*;
import com.boileradvsr.backend.models.repositories.EventRepository;
import com.boileradvsr.backend.models.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@EnableMongoRepositories
@RequestMapping("/events")
public class EventController {
    public EventRepository repository;

    @Autowired
    public StudentRepository srepo;

    public EventController(EventRepository repository) {this.repository = repository;}

    @Autowired
    public EventReminderService reminderService;

    @Scheduled(fixedRate = 86400000, initialDelay = 5000)
    public void execute() {

        LocalDate localDate = LocalDate.now();
        int day = localDate.getDayOfMonth();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        for (Event r:
                repository.findAll()) {
            if (r.getYear() == year) {
                if (r.getMonth() == month){
                    if (r.getDay() == day) {
                        //todo
                        List<Student> list = srepo.findAll();
                        reminderService.upcoming(list, r);
                    }
                }
            }

        }
        localDate = localDate.plusDays(1);
        day = localDate.getDayOfMonth();
        month = localDate.getMonthValue();
        year = localDate.getYear();
        for (Event r: repository.findAll()) {
            if (r.getYear() == year) {
                if (r.getMonth() == month){
                    if (r.getDay() == day) {
                        List<Student> list = srepo.findAll();
                        reminderService.upcoming(list, r);
                    }
                }
            }

        }
    }

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
