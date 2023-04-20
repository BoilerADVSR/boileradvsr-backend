package com.boileradvsr.backend.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="calendar")
public class Event {
    private String title;
    private String date;

    public Event(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
