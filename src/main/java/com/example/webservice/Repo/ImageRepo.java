package com.example.webservice.Repo;

import com.example.webservice.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {
    Image findByUserId(String user_id);
    Boolean existsByUserId(String user_id);
}
