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

    public Message(String senderId, Course course, String receiverId) {
        this.id = UUID.randomUUID().toString();
        this.senderId = senderId;
        text = formatCourse(course, receiverId);

    }


    public String formatCourse(Course course, String receiverId) {
        String link = "localhost:3000/" + receiverId + "/" + course.getCourseID();
        StringBuffer sb = new StringBuffer();
        sb.append(course.getCourseID()).append("\n");
        sb.append(course.getCourseTitle()).append("\n");
        sb.append("Credit Hours:").append(course.getCreditHours()).append("\n");
        sb.append("LINK: ").append(link);

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
