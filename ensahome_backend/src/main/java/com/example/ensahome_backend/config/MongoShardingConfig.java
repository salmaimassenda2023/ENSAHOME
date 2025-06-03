package com.example.ensahome_backend.config;

import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import jakarta.annotation.PostConstruct;

@Configuration
public class MongoShardingConfig {

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void initializeSharding() {
        try {
            // Vérifier si la collection est déjà shardée
            Document listShards = mongoClient.getDatabase("admin")
                    .runCommand(new Document("listShards", 1));
            
            // Si nous arrivons ici, le sharding est déjà configuré
            System.out.println("Sharding configuration already exists");
            
        } catch (Exception e) {
            System.out.println("Error checking sharding status: " + e.getMessage());
        }
    }
} 