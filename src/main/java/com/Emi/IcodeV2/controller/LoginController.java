package com.Emi.IcodeV2.controller;

import com.Emi.IcodeV2.model.User;
import com.Emi.IcodeV2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class LoginController {
    @Autowired
    UserRepository userRepository;



    @GetMapping("/fakeGet")
     public List<User> fakeGetUser(){ // normal prend aucun argument puis return type is liste of users
        return userRepository.findAll();
    }


    @PostMapping("/signup")
    public ResponseEntity<?> fakeCreateUser(@RequestBody User user){  // argument dyalha is like axno fdak body dyal hadik post retourn a la base de donne le user
        userRepository.save(user);
        return ResponseEntity.ok().body("success");
    }

    @PostMapping ("/isAdmin")
    public  ResponseEntity<?> isAdmin(@RequestParam(name = "username") String username ) {
        User foundUser;
         foundUser=  userRepository.findUserByUsername(username);
         if(foundUser.getRole().equals("user")) return ResponseEntity.ok().body("no");
         return  ResponseEntity.ok().body("yes");
    }




}
