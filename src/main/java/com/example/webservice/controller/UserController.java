package com.example.webservice.controller;

import com.example.webservice.GetUser;
import com.example.webservice.exception.EmailExistException;
import com.example.webservice.exception.NotValidEmailException;
import com.example.webservice.exception.ResourceNotFoundException;
import com.example.webservice.model.User;
import com.example.webservice.repository.NoteRepository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
//@RequestMapping("/api")
public class UserController {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    NoteRepository noteRepository;

    // Get All Notes
    @GetMapping("/v1/user/self")
    public GetUser getAllNotes() {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        User userData=noteRepository.findByEmailAddress(auth.getName());

        GetUser getUser=new GetUser();
        getUser.setId(userData.getId());
        getUser.setUsername(userData.getEmailAddress());
        getUser.setFirst_name(userData.getFirstname());
        getUser.setLast_name(userData.getLastname());
        getUser.setAccount_created(userData.getCreatedAt());
        getUser.setAccount_updated(userData.getUpdatedAt());

        return getUser;
    }




    // Create a new Note
    @PostMapping("/v1/user")
    public GetUser createNote(@Valid @RequestBody User note) {
//        System.out.println("!!!");

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(note.getEmailAddress());
        Response response = new Response();


        if (!matcher.matches()) {
            throw new NotValidEmailException();
        }
        if (noteRepository.findByEmailAddress(note.getEmailAddress())!=null){
            throw new EmailExistException();
        }

        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        note.setPassword(bCryptPasswordEncoder.encode(note.getPassword()));
//        return new GetUser();
        return GetUser.saveToNewUser(noteRepository.save(note));
    }

    // Get a Single Note
    @GetMapping("/users/{id}")
    public User getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    // Update a Note
    @PutMapping("/v1/users/self")
    public ResponseEntity<?> updateNote(@Valid @RequestBody User noteDetails) {

        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        User userData=noteRepository.findByEmailAddress(auth.getName());

        if (!userData.getEmailAddress().equals(noteDetails.getEmailAddress())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        userData.setFirstname(noteDetails.getFirstname());
        userData.setLastname(noteDetails.getLastname());
        userData.setPassword(passwordEncoder.encode(noteDetails.getPassword()));
//        noteRepository.save(userData);
        User updatedNote = noteRepository.save(userData);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
//        return updatedNote;
    }

    // Delete a Note
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        User note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}
