package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.UserAccount;
import com.examly.springapp.repository.UserAccountRepo;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepo userAccountRepo;

    @Override
    public UserAccount saveUser(UserAccount user) {
        return userAccountRepo.save(user);
    }
}
