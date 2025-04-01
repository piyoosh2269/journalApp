package com.healCare.JournalApp.Controller;

import com.healCare.JournalApp.Entity.JournalEntry;
import com.healCare.JournalApp.Entity.User;
import com.healCare.JournalApp.service.JournalEntryService;
import com.healCare.JournalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;//linking or injecting the JournalEntryService(journalEntryService is just an instance)
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<?> getallJournalEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByuserName(userName);//no need of this line
        List<JournalEntry> all = journalEntryService.getAll();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public  ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            journalEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByuserName(userName);
       List<JournalEntry> collect =  user.getJournalEntries().stream().filter(x-> x.getId().equals(myId)).collect(Collectors.toList());
       if(!collect.isEmpty()){
           Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);//finding the entry by myId
           if(journalEntry.isPresent()){//if the journal entry is present,
               return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);//then return all the entry and give OK status code
           }
       }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);//otherwise just show the statuscode NOT_FOUND
    }
    @DeleteMapping("/id/{myid}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
       boolean removed =  journalEntryService.deleteById(myid,username);
       if(removed){
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }else{
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }
    @PutMapping("/id/{id}")
    public ResponseEntity updateJournalEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByuserName(userName);
        List<JournalEntry> collect =  user.getJournalEntries().stream().filter(x-> x.getId().equals(id)).collect(Collectors.toList());

        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.findById(id);//finding the entry by myId
            if(journalEntry.isPresent()){//if the journal entry is present,
                JournalEntry old = journalEntry.get();
                old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")? newEntry.getContent(): old.getContent());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(old,HttpStatus.OK);
            }
        }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




}
