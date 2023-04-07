package com.boileradvsr.backend.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.UUID;
import java.util.random.RandomGenerator;

public class Question {
    public enum discussionType {
        QUESTION,
        RESPONSE
    }
    private String id;
    private discussionType type;
    private String text;
    private String userID;
    private ArrayList<Question> responses;
    public Question(String userID, String text, discussionType type) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.text = text;
        this.userID = userID;
        if (type == discussionType.QUESTION) {
            responses = new ArrayList<>();
        }
    }

    public Question() {}

    public ArrayList<Question> getResponses() {
        return responses;
    }

    public discussionType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public String getUserID() {
        return userID;
    }

    public void setType(discussionType type) {
        this.type = type;
    }

    public void setResponses(ArrayList<Question> responses) {
        this.responses = responses;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
