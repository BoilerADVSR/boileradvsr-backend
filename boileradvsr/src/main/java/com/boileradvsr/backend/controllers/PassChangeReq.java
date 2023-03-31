package com.boileradvsr.backend.controllers;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassChangeReq {
    private String email;
    public String getEmail() {
        return email;
    }
}