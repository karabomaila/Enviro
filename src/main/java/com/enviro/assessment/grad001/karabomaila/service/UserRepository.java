package com.enviro.assessment.grad001.karabomaila.service;

import com.enviro.assessment.grad001.karabomaila.model.AccountProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<AccountProfile, Long> {
    public AccountProfile findAccountProfileByFirstNameAndAndLastName(String firstName, String lastName);
}
