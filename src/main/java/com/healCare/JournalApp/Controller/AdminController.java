package com.healCare.JournalApp.Controller;

import com.healCare.JournalApp.Entity.User;
import com.healCare.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//this file is used to provide role base authorization like ADMIN role has access to post or create an ADMIN , can see all entries and all the users.




@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;


    @GetMapping("/all-users")
    public ResponseEntity<?> getallUsers(){
        List<User> all = userService.getAll();//storing all the users with entries in the all list
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/create-admin-user")
    public void create(@RequestBody User user){
        userService.saveAdmin(user);
    }

}
