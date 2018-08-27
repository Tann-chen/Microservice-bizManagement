package com.inventory.service;

import com.inventory.comm.exception.JsonParseException;
import com.inventory.domain.remote.ResponseUser;
import com.inventory.domain.remote.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserInfoService {

    @Autowired
    private RestTemplate restTemplate;

    public User getUserInfoById(Long userId) throws Exception {
        ResponseUser responseUser = restTemplate.getForObject("http://biz-user/user/{1}", ResponseUser.class, userId);

        if(responseUser.getCode() != 200){
            throw new IllegalArgumentException(responseUser.getMessage());
        }

        if(responseUser.getData() == null){
            throw new JsonParseException("user");
        }

        return responseUser.getData();
    }

}
