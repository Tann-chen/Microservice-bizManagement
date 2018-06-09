package com.user.controller;

import com.user.comm.exception.JsonParseException;
import com.user.comm.result.Result;
import com.user.comm.result.ResultBuilder;
import com.user.domain.entity.User;
import com.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(method = RequestMethod.POST)
    public Result register(@RequestBody User user) throws Exception {
        if (null == user) {
            throw new JsonParseException("user");
        }
        Long newUserId = userInfoService.createUser(user);

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(newUserId)
                .build();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Result getUserDetails(@PathVariable Long userId) throws Exception{
        if (null == userId) {
            throw new JsonParseException("userId");
        }
        User user = userInfoService.findUserById(userId);

        ResultBuilder resultBuilder = new ResultBuilder().setCode(200);
        if (null == user) {
            resultBuilder.setMessage("user not existed");
        } else {
            resultBuilder.setData(user);
        }

        return resultBuilder.build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result getUserList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<User> userList = userInfoService.findAllUsersByPage(new PageRequest(page, size));
        return new ResultBuilder()
                .setCode(200)
                .setData(userList)
                .build();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public Result updateUserDetails(@PathVariable Long userId, @RequestBody User user) throws Exception{
        if (null == userId) {
            throw new JsonParseException("userId");
        }
        if (null == user) {
            throw new JsonParseException("user");
        }
        userInfoService.updateUser(userId, user);

        return new ResultBuilder().setCode(200).build();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public Result deleteUser(@PathVariable Long userId) throws Exception{
        if (null == userId) {
            throw new JsonParseException("userId");
        }
        userInfoService.changeIsActiveStatus(userId,false);

        return new ResultBuilder().setCode(200).build();
    }

    @RequestMapping(value = "/{userId}/act:recover")
    public Result recoverUser(@PathVariable Long userId) {
        if (null == userId) {
            throw new JsonParseException("userId");
        }
        userInfoService.changeIsActiveStatus(userId, true);

        return new ResultBuilder().setCode(200).build();
    }

}
