package com.user.controller;

import com.user.comm.exception.JsonParseException;
import com.user.comm.result.Result;
import com.user.comm.result.ResultBuilder;
import com.user.domain.entity.User;
import com.user.service.UserInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation("Add new user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "length<45", dataType = "string"),
            @ApiImplicitParam(name = "email", value = "length<45", required = true, dataType = "string"),
            @ApiImplicitParam(name = "phone", value = "length<45", dataType = "string"),
            @ApiImplicitParam(name = "password", value = "length<45", required = true, dataType = "string"),
            @ApiImplicitParam(name = "role_str_lst", value = "list(string)", dataType = "list"),
            @ApiImplicitParam(name = "job_status", value = "undistributed, part_time, full_time, retired, dismissed", dataType = "string")
    })
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

    @ApiOperation("Get details of one user")
    @ApiImplicitParam(name = "userId", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Result getUserDetails(@PathVariable Long userId) throws Exception {
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

    @ApiOperation("Get list of all users by page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "page", dataType = "int", paramType = "param"),
            @ApiImplicitParam(name = "size", value = "size", dataType = "int", paramType = "param")
    })
    @RequestMapping(method = RequestMethod.GET)
    public Result getUserList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<User> userList = userInfoService.findAllUsersByPage(new PageRequest(page, size));
        return new ResultBuilder()
                .setCode(200)
                .setData(userList)
                .build();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    @ApiOperation("update details of user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "email", value = "length<45", required = true, dataType = "string"),
            @ApiImplicitParam(name = "phone", value = "length<45", dataType = "string"),
            @ApiImplicitParam(name = "password", value = "length<45", required = true, dataType = "string"),
            @ApiImplicitParam(name = "role_str_lst", value = "list(string)", dataType = "list"),
            @ApiImplicitParam(name = "job_status", value = "undistributed, part_time, full_time, retired, dismissed", dataType = "string")
    })
    public Result updateUserDetails(@PathVariable Long userId, @RequestBody User user) throws Exception {
        if (null == userId) {
            throw new JsonParseException("userId");
        }
        if (null == user) {
            throw new JsonParseException("user");
        }
        userInfoService.updateUser(userId, user);

        return new ResultBuilder().setCode(200).build();
    }

    @ApiOperation("Delete one user")
    @ApiImplicitParam(name = "userId", required = true, dataType = "int")
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public Result deleteUser(@PathVariable Long userId) throws Exception {
        if (null == userId) {
            throw new JsonParseException("userId");
        }
        userInfoService.changeIsActiveStatus(userId, false);

        return new ResultBuilder().setCode(200).build();
    }

}