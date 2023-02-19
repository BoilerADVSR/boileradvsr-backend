package com.backend.boileradvsr.boileradvsr;

import java.util.ArrayList;
import java.util.List;
import com.backend.boileradvsr.boileradvsr.Course.COURSETYPE;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ClusterManager {
    //user: ADVSRbackend
    //pw: 0VbpUPMPQgjN1wAM


    public static void main(String[] args) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        // Replace the uri string with your MongoDB deployment's connection string

        String uri = "mongodb+srv://ADVSRbackend:0VbpUPMPQgjN1wAM@advsrcluster.1ngfkqw.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("CourseCatalog").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Course> computerScienceCollection = database.getCollection("CScollection", Course.class);
            Course cs180 = new Course("CS", "180", "Programming I", "Computer Science", "Science", COURSETYPE.DATABASE);
            computerScienceCollection.insertOne(cs180);

            List<Course> coursesList = new ArrayList<>();
            computerScienceCollection.find().into(coursesList);
            System.out.println(coursesList);
            //System.out.println(movie);
        }
    }
}