package com.boileradvsr.backend.controllers;

import ch.qos.logback.core.model.Model;
import com.boileradvsr.backend.models.CourseRepository;
import com.boileradvsr.backend.models.*;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@EnableMongoRepositories
@RequestMapping("/photos")
public class PhotoController {
    PhotoRepository repository;

    public PhotoController(PhotoRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/add")
    public String addPhoto(@RequestParam("title") String title, @RequestParam("image") MultipartFile image, Model model) throws IOException {
        Photo photo = new Photo(title);
        photo.setImage(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
        repository.save(photo);
        return photo.getId();
    }
    @GetMapping("/{id}")
    public Photo getPhoto(@PathVariable String id)  {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }


}
