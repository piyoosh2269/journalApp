package com.healCare.JournalApp.Controller;

import com.healCare.JournalApp.Entity.User;
import com.healCare.JournalApp.Repository.UserRepository;
import com.healCare.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


//    @GetMapping
//    public ResponseEntity<?> getall(){//just tried by myself
//        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
//        String userName = authentication.getName();
//        List<User>all = userService.getAll();
//        if(all!=null && !all.isEmpty()){
//            return new ResponseEntity<>(all,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userIndb=userService.findByuserName(userName);
            userIndb.setUserName(user.getUserName());
            userIndb.setPassword(user.getPassword());
            userService.saveNewUser(userIndb);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
