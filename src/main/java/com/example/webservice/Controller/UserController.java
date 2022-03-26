package com.example.webservice.Controller;

import com.example.webservice.Model.User;
import com.example.webservice.Model.UserDetail;
import com.example.webservice.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepo userRepo;

    @GetMapping("/v1/user/self")
    public ResponseEntity<?> getUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userInfo = userRepo.findByUsername(auth.getName());

        return new ResponseEntity(new UserDetail(userInfo), HttpStatus.OK);
    }

    @GetMapping("/healthz")
    public String getEndPoint() {
        return String.format(" ");
    }

    @PostMapping("/v1/user")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        if (userRepo.existsByUsername(user.getUsername())){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return new ResponseEntity(new UserDetail(user), HttpStatus.CREATED);
    }

    @PutMapping("/v1/user/self")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userInfo = userRepo.findByUsername(auth.getName());

        System.out.println(user.getUsername());

        if (!userInfo.getUsername().equals(user.getUsername())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        userInfo.setFirst_name(user.getFirst_name());
        userInfo.setLast_name(user.getLast_name());
        userInfo.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(userInfo);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    // Get a Single Note

    // Update a Note

    // Delete a Note
}