//package com.healCare.JournalApp.Controller;
//
//import com.healCare.JournalApp.Entity.JournalEntry;
//import com.healCare.JournalApp.service.JournalEntryService;
//import org.bson.types.ObjectId;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@RestController
//@RequestMapping("/Practice")
//public class Practice {
//    @Autowired
//    private JournalEntryService journalEntryService;//injecting the JOURNAL_ENTRY_SERVICE.
//
//    @GetMapping
//    public ResponseEntity getAll(){
//        List<JournalEntry>all = journalEntryService.getAll();
//        if(all!=null  && !all.isEmpty()){
//            return new ResponseEntity<>(all,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//    }
//
//    @PostMapping
//    public ResponseEntity<?> Post(@RequestBody JournalEntry myEntry){
//        try {
//            journalEntryService.saveEntry(myEntry);
//            myEntry.setDate(LocalDateTime.now());
//            return new ResponseEntity("CREATED",HttpStatus.CREATED);
//        }catch (Exception e){
//           return new  ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//
//    }
//    @DeleteMapping("/id/{myId}")
//    public ResponseEntity<?> DeleteAll(@PathVariable ObjectId myId){
//        journalEntryService.deleteById(myId);
//        return  new ResponseEntity<>("deleted",HttpStatus.ACCEPTED);
//
//
//    }
//}
