package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.UserAccount;
import com.examly.springapp.service.UserAccountService;

@RestController
@RequestMapping("/api")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping("/users")
    public UserAccount createUser(@RequestBody UserAccount user) {
        return userAccountService.saveUser(user);
    }
}
