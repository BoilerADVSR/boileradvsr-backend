package com.boileradvsr.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.UUID;

@Document(collection = "Chats")
public class Chat {
    @Id
    private String id;
    private ArrayList<String> names;
    private ArrayList<Message> messages;

    public Chat(String student1, String student2) {
        this.id = UUID.randomUUID().toString();
        names = new ArrayList<>();
        names.add(student1);
        names.add(student2);
        messages = new ArrayList<>();
    }

    public Chat() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setNames(ArrayList<String> names) {
        this.names = names;
    }


    public ArrayList<String> getNames() {
        return names;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id='" + id + '\'' +
                ", names=" + names +
                ", messages=" + messages +
                '}';
    }
}
