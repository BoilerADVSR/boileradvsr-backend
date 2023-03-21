package com.boileradvsr.backend.controllers;

import com.boileradvsr.backend.configurations.JwtService;
import com.boileradvsr.backend.models.Student;
import com.boileradvsr.backend.models.StudentRepository;
import com.boileradvsr.backend.models.Token;
import com.boileradvsr.backend.models.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final StudentRepository studentRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        if (studentRepository.findByEmail(request.getEmail()).isEmpty()) {
            var student = Student.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();
            var savedStudent = studentRepository.save(student);
            var jwtToken = jwtService.generateToken(student);
            saveStudentToken(savedStudent, jwtToken);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } else {
            throw new RuntimeException();
        }
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var student = studentRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(student);
        revokeAllStudentTokens(student);
        saveStudentToken(student, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void saveStudentToken(Student student, String jwtToken) {
        var token = Token.builder()
                .student(student)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllStudentTokens(Student student) {
        var validStudentTokens = tokenRepository.findAllValidTokenByStudent(student.getEmail());
        if (validStudentTokens.isEmpty())
            return;
        validStudentTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validStudentTokens);
    }
}
