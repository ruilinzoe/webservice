package com.example.webservice.S3Config;

import com.example.webservice.Model.Image;
import com.example.webservice.Model.User;
import com.example.webservice.Repo.ImageRepo;
import com.example.webservice.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AWSS3Ctrl {
    @Value("${aws.s3.region}")
    public String region;

    @Value("${aws.s3.bucket}")
    public String bucketName;

    @Autowired
    private AWSS3Service service;

    @Autowired
    UserRepo userRepo;

    @Autowired
    ImageRepo imageRepo;

    @PostMapping(value= "/v1/user/self/pic")
    public ResponseEntity<?> uploadFile(@RequestPart(value= "profilePic") final MultipartFile multipartFile) {
        Image pi = new Image();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userInfo = userRepo.findByUsername(auth.getName());
        String userID = userInfo.getUuid();

        if (imageRepo.existsByUserId(userID)) {
            Image image =  imageRepo.findByUserId(userID);
            imageRepo.delete(image);
            service.deleteFile(userID + "/" + image.getFile_name());
        }

        String fileName = multipartFile.getOriginalFilename();
        String url = "https://s3." + region + ".amazonaws.com/"+ bucketName + "/" + userInfo.getUuid() + "/" + fileName;

        pi.setUserId(userInfo.getUuid());
        pi.setFile_name(fileName);
        pi.setUrl(url);
        imageRepo.save(pi);
        service.uploadFile(multipartFile,userInfo.getUuid());
        return new ResponseEntity<>(pi, HttpStatus.CREATED);
    }

    @GetMapping (value= "/v1/user/self/pic")
    public ResponseEntity<?> getImage () {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userInfo = userRepo.findByUsername(auth.getName());
        String userID = userInfo.getUuid();
        if (!imageRepo.existsByUserId(userID)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Image image =  imageRepo.findByUserId(userID);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @DeleteMapping(value= "/v1/user/self/pic")
    public ResponseEntity<?> deletePic () {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userInfo = userRepo.findByUsername(auth.getName());
        String userID = userInfo.getUuid();
        if (!imageRepo.existsByUserId(userID)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Image image =  imageRepo.findByUserId(userID);
        imageRepo.delete(image);
        service.deleteFile(userID + "/" + image.getFile_name());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping(value= "/v1/user/self/picture")
//    public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam(value= "") final String keyName) {
//        final byte[] data = service.downloadFile(keyName);
//        final ByteArrayResource resource = new ByteArrayResource(data);
//        return ResponseEntity
//                .ok()
//                .contentLength(data.length)
//                .header("Content-type", "application/octet-stream")
//                .header("Content-disposition", "attachment; filename=\"" + keyName + "\"")
//                .body(resource);
//    }

}