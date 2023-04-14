package com.boileradvsr.backend.controllers;

import com.boileradvsr.backend.models.Student;
import com.boileradvsr.backend.models.repositories.StudentRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    @Autowired
    public StudentRepository studentRepository;

    public void addImage(Student s, MultipartFile file) throws IOException {
        s.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
    }

    public Binary rImage(Student s) {
        return s.getImage();
    }
}