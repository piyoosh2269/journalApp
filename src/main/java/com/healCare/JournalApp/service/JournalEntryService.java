package com.healCare.JournalApp.service;

import com.healCare.JournalApp.Entity.JournalEntry;
import com.healCare.JournalApp.Entity.User;
import com.healCare.JournalApp.Repository.JournalRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {
@Autowired
    private JournalRepository journalRepository;
@Autowired
private UserService userService;



@Transactional
public void saveEntry(JournalEntry journalEntry, String userName) {
    try {
        User user = userService.findByuserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);
    } catch (Exception e) {
        throw new RuntimeException("An error occurred while saving the entry.", e);
    }
}
    public void saveEntry(JournalEntry journalEntry){

        journalRepository.save(journalEntry);
    }
public List<JournalEntry> getAll() {
    return journalRepository.findAll();
}

public Optional<JournalEntry> findById(ObjectId id){
    return journalRepository.findById(id);

}
@Transactional
public boolean deleteById(ObjectId  id, String userName){
    boolean removed=false;
    try {
        User user = userService.findByuserName(userName);
        removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        if(removed){
            userService.saveUser(user);
            journalRepository.deleteById(id);
        }
    } catch (Exception e){
        log.error("Error",e);
        throw new RuntimeException("An error occurred while deleting the entry,",e);
    }
    return removed;
}
}
