package com.enviro.assessment.grad001.karabomaila.controller;

import com.enviro.assessment.grad001.karabomaila.model.AccountProfile;
import com.enviro.assessment.grad001.karabomaila.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/v1/api/image")
public class ImageController {
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{name}/{surname}")
    public FileSystemResource getHttpImageLink(@PathVariable String name, @PathVariable String surname){
        AccountProfile accountProfile = userService.findUserProfile(name, surname);

        return new FileSystemResource(new File(accountProfile.getImageLink()));
    }
}
