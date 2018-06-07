package com.user.controller;


import com.user.comm.Result.Result;
import com.user.comm.Result.ResultBuilder;
import com.user.comm.exception.BizException;
import com.user.comm.exception.SystemException;
import com.user.domain.entity.User;
import com.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(method = RequestMethod.POST)
    public Result register(@RequestBody User user){
        try{
            Long userId = userInfoService.createUser(user);
            Map<String, Long> data = new HashMap<>();
            data.put("new_user_id", userId);
            return new ResultBuilder()
                    .setCode(ResultBuilder.SUCCESS)
                    .setData(data).build();
        }catch (BizException bizEx){
            return new ResultBuilder()
                    .setCode(ResultBuilder.BIZ_ERROR)
                    .setMessage(bizEx.getMessage())
                    .build();
        }catch (SystemException sysEx){
            return new ResultBuilder()
                    .setCode(ResultBuilder.SYSTEM_ERROR)
                    .setMessage(sysEx.getMessage())
                    .build();
        }
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Result getUserInfo(@PathVariable Long userId){
        if(null == userId){
            return new ResultBuilder()
                    .setCode(ResultBuilder.BIZ_ERROR)
                    .setMessage("userId can not be null")
                    .build();
        }

        User user = userInfoService.findUserById(userId);
        ResultBuilder resultBuilder = new ResultBuilder();
        resultBuilder.setCode(ResultBuilder.SUCCESS);
        if(null == user){
            resultBuilder.setMessage("the user is not existed");
        }else{
            resultBuilder.setData(user);
        }

        return resultBuilder.build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result getUserList(){
        List<User> userList = userInfoService.findAllUsers();
        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(userList)
                .build();
    }


    @RequestMapping(value = "/{userId}",method = RequestMethod.PUT)
    public Result updateUserInfo(@PathVariable Long userId, @RequestBody User user){
        if(null == userId){
            return new ResultBuilder()
                    .setCode(ResultBuilder.BIZ_ERROR)
                    .setMessage("userId can not be null")
                    .build();
        }

        try{
            userInfoService.updateUser(userId, user);
            return new ResultBuilder()
                    .setCode(ResultBuilder.SUCCESS)
                    .build();

        } catch (BizException bizEx){
            return new ResultBuilder()
                    .setCode(ResultBuilder.BIZ_ERROR)
                    .setMessage(bizEx.getMessage())
                    .build();
        } catch (SystemException sysEx){
            return new ResultBuilder()
                    .setCode(ResultBuilder.SYSTEM_ERROR)
                    .setMessage(sysEx.getMessage())
                    .build();
        }

    }

    @RequestMapping(value = "/{userId}",method = RequestMethod.DELETE)
    public Result deleteUser(@PathVariable Long userId){
        if(null == userId){
            return new ResultBuilder()
                    .setCode(ResultBuilder.BIZ_ERROR)
                    .setMessage("userId can not be null")
                    .build();
        }

        try{
            userInfoService.deleteUser(userId);
            return new ResultBuilder()
                    .setCode(ResultBuilder.SUCCESS)
                    .build();
        } catch (BizException bizEx){
            return new ResultBuilder()
                    .setCode(ResultBuilder.BIZ_ERROR)
                    .setMessage("userId can not be null")
                    .build();
        }
    }


}
