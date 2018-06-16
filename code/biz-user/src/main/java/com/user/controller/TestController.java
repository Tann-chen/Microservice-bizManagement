package com.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @RequestMapping(method = RequestMethod.GET)
    public String testFunc(){
        return "hello, user!";
    }
}
