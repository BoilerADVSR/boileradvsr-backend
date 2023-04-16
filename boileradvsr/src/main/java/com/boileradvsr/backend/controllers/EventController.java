package com.boileradvsr.backend.controllers;

import com.boileradvsr.backend.models.DegreeGraph;
import com.boileradvsr.backend.models.*;
import com.boileradvsr.backend.models.repositories.EventRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@EnableMongoRepositories
@RequestMapping("/events")
public class EventController {
    public EventRepository repository;

    public EventController(EventRepository repository) {this.repository = repository;}

    @Scheduled(fixedRate = 86400000)
    public void execute() {

        LocalDate localDate = LocalDate.now();
        int day = localDate.getDayOfMonth();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        for (Event r:
             repository.findAll()) {
            if (r.getYear() == year) {
                if (r.getMonth() == month){
                    if (r.getDay() == day || r.getDay() == day + 1) {
                        //todo
                        System.out.println("event today!");
                    }
                }
            }

        }
        localDate = localDate.plusDays(1);
        day = localDate.getDayOfMonth();
        month = localDate.getMonthValue();
        year = localDate.getYear();
        for (Event r:
                repository.findAll()) {
            if (r.getYear() == year) {
                if (r.getMonth() == month){
                    if (r.getDay() == day || r.getDay() == day + 1) {
                        //todo
                        System.out.println("event today!");
                    }
                }
            }

        }
    }

    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return repository.findAll();
    }


}
