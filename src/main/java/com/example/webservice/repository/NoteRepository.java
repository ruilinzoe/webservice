package com.example.webservice.repository;

import com.example.webservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<User, Long> {
    User findByEmailAddress(String emailAddress);
}


