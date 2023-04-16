package com.boileradvsr.backend.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="calendar")
public class Event {
    private String title;
    private int year;
    private int month;
    private int day;

    public Event(String title, int year, int month, int day) {
        this.title = title;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
