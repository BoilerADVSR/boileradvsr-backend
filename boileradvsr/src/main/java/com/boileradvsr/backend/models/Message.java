package com.boileradvsr.backend.models;

import java.util.Date;
import java.util.UUID;

public class Message {
    private String id;
    private String text;
    private String senderId;

    public Message(String senderId, String text) {
        this.id = UUID.randomUUID().toString();
        this.senderId = senderId;
        this.text = text;
    }

    public Message(String senderId, Course course) {
        this.id = UUID.randomUUID().toString();
        this.senderId = senderId;
        text = formatCourse(course);

    }


    public String formatCourse(Course course) {
        StringBuffer sb = new StringBuffer();
        sb.append(course.getCourseID()).append("\n");
        sb.append(course.getCourseTitle()).append("\n");
        sb.append("Credit Hours:" + course.getCreditHours()).append("\n");
        sb.append("LINK:");

        return sb.toString();
    }



    public Message() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getText() {
        return text;
    }
}
