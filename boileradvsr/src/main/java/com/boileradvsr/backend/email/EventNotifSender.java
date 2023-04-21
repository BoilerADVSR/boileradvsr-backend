package com.boileradvsr.backend.email;
public interface EventNotifSender {
    void send(String to, String email);
}