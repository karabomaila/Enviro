package com.enviro.assessment.grad001.karabomaila.service;

import com.enviro.assessment.grad001.karabomaila.model.AccountProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUserProfile(AccountProfile accountProfile){
        userRepository.save(accountProfile);
    }

    public AccountProfile findUserProfile(String name, String surname){
        return userRepository.findAccountProfileByFirstNameAndAndLastName(name, surname);
    }
}
