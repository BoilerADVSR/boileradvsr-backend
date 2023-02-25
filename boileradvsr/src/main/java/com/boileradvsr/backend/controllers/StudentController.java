package com.boileradvsr.backend.controllers;

import com.boileradvsr.backend.models.Student;
import com.boileradvsr.backend.models.StudentRepository;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentController {
        public StudentRepository studentRepo;

        public void addStudent(Student student) {
            studentRepo.save(new Student("Garrett", "O'Brien", "g@purdue.edu", "pw"));
        }

        public Student findByEmail(String email) {
<<<<<<< HEAD
=======
            Student student =  studentRepo.findById(email);
>>>>>>> c889efa (controller/repository)
            return null;
        }

}
