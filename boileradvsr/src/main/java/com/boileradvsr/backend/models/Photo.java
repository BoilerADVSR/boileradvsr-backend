package com.boileradvsr.backend.models;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="photos")
public class Photo {
    @Id
    String id;
    String title;
    Binary image;

    public Photo(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public Binary getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(Binary image) {
        this.image = image;
    }
}
