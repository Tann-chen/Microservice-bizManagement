package com.user.controller;

import com.user.comm.Result.Result;
import com.user.domain.entity.User;
import com.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(name = "")
    public Result register(User user){

    }


}
