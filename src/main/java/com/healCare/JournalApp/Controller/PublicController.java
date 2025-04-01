package com.healCare.JournalApp.Controller;

import com.healCare.JournalApp.Entity.User;
import com.healCare.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;
    @GetMapping("/health-check")
    public String healthcheck(){
        return "KYA RE LUND KE!!!";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }

}
