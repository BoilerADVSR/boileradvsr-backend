package com.boileradvsr.backend.email;

public interface NotifSender {
    void send(String to, String email);
}