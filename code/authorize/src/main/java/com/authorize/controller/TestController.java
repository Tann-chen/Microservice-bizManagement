package com.authorize.controller;

import com.authorize.entity.User;
import com.authorize.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/get_user")
    public User getUser() {
        User u = userRepository.queryByEmail("tester1@gmail.com");
        return u;
    }
}
