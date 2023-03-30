package com.boileradvsr.backend.controllers;

//import com.boileradvsr.backend.models.;

import com.boileradvsr.backend.models.Student;
import com.boileradvsr.backend.models.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RequiredArgsConstructor
@Service
public class FileStorageService {

//    private final \ fileDBRepository;

    private final StudentRepository studentRepository;

    public void store(MultipartFile file, String email) throws IOException {
        Student student = studentRepository.findByEmail(email).orElseThrow();
        student.setProfilePic(file.getBytes());
        studentRepository.save(student);

//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        FileDB image = new FileDB(fileName, file.getContentType(), file.getBytes());
//        Student student = studentRepository.findByEmail(email).get();
//        image.setStudent(student);
//        fileDBRepository.save(image);
//        FileDB image2;
//
//        file.getBytes() = image2.getData();
    }

    public byte[] getFile(String email) {
        Student student = studentRepository.findByEmail(email).orElseThrow();
        return student.getProfilePic();
    }
}
