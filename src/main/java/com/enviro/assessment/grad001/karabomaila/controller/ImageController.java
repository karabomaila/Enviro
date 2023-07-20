package com.enviro.assessment.grad001.karabomaila.controller;

import com.enviro.assessment.grad001.karabomaila.model.AccountProfile;
import com.enviro.assessment.grad001.karabomaila.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("/v1/api/image")
public class ImageController {
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{name}/{surname}", produces = {"image/jpeg", "image/png", "image/jpg"})
    public FileSystemResource getHttpImageLink(@PathVariable String name, @PathVariable String surname){
        AccountProfile accountProfile = userService.findUserProfile(name, surname);
        try{
            URI uri = new URI(accountProfile.getImageLink());
            return new FileSystemResource(uri.getPath());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
