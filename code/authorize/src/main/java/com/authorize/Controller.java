package com.authorize;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth/test")
public class Controller {

    @RequestMapping(method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }
}
