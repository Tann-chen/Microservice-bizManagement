package com.user.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping(method = RequestMethod.GET)
    public String testFunc(){
        return "hello, user!";
    }

    @RequestMapping(value = "/get_current", method = RequestMethod.GET)
    public Object getCurrentUser() {
        Object current =  SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return current;
    }

    @PreAuthorize("hasAuthority('user_read')")
    @RequestMapping(value = "/get_success", method = RequestMethod.GET)
    public String testMethodSecuritySuccess() {
        return "success";
    }

    @PreAuthorize("hasAuthority('user_reload')")
    @RequestMapping(value = "/get_refuse", method = RequestMethod.GET)
    public String testMethodSecurityRefuse() {
        return "refuse";
    }

}
