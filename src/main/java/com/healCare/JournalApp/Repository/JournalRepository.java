package com.healCare.JournalApp.Repository;

import com.healCare.JournalApp.Entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JournalRepository extends MongoRepository<JournalEntry, ObjectId> {


//    List<JournalEntry> findByUserName(String userName);
}

