package com.user.controller;

import com.user.comm.exception.JsonParseException;
import com.user.comm.result.Result;
import com.user.comm.result.ResultBuilder;
import com.user.domain.entity.User;
import com.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private TokenStore tokenStore;

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Result getCurrentUser(OAuth2Authentication auth) {
        final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
        final OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
        Map<String, Object> tokenDetails = accessToken.getAdditionalInformation();
        Long currentUserId = (Long) tokenDetails.get("user_id");
        User currentUser = userInfoService.findUserById(currentUserId);


        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(currentUser)
                .build();
    }


    @RequestMapping(method = RequestMethod.POST)
    public Result register(@RequestBody User user) throws Exception {
        if (null == user) {
            throw new JsonParseException("user");
        }

        Long newUserId = userInfoService.createUser(user);

        List<User> userList = userInfoService.findAllUsersByPage();

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(userList)
                .build();
    }


    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Result getUserDetails(@PathVariable Long userId) throws Exception {
        if (null == userId) {
            throw new JsonParseException("userId");
        }
        User user = userInfoService.findUserById(userId);

        ResultBuilder resultBuilder = new ResultBuilder();
        if (null == user) {
            resultBuilder.setCode(ResultBuilder.FAILED).setMessage("user not existed");
        } else {
            resultBuilder.setCode(ResultBuilder.SUCCESS).setData(user);
        }

        return resultBuilder.build();
    }


    @RequestMapping(method = RequestMethod.GET)
    public Result getUserList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        List<User> userList = userInfoService.findAllUsersByPage();
        return new ResultBuilder()
                .setCode(200)
                .setData(userList)
                .build();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public Result updateUserDetails(@PathVariable Long userId, @RequestBody User user) throws Exception {
        if (null == userId) {
            throw new JsonParseException("userId");
        }
        if (null == user) {
            throw new JsonParseException("user");
        }
        User newUserInfo = userInfoService.updateUser(userId, user);

        return new ResultBuilder().setCode(ResultBuilder.SUCCESS).setData(newUserInfo).build();
    }


    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public Result deleteUser(@PathVariable Long userId) throws Exception {
        if (null == userId) {
            throw new JsonParseException("userId");
        }
        userInfoService.changeIsActiveStatus(userId, false);

        List<User> userList = userInfoService.findAllUsersByPage();

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(userList)
                .build();
    }

}
