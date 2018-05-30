package com.tan.users.service;

import com.tan.users.domain.User;
import com.tan.users.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserRepository userRepository;

    private UserService userService;


    @Before
    public void setUp() throws Exception {
        userService = new UserService();
    }

    @Test
    public void createUser() throws Exception {
        long beforeCount = userRepository.count();
        User user = new User();
        user.setGender("female");
        user.setEmail("test@test.com");
        user.setPassword("111111");
        user.setPassword("111111");
        user.setIsActive(false);
        userService.createUser(user);
        long afterCount = userRepository.count();
        Assert.assertEquals(1,afterCount-beforeCount);
    }

    @Test
    public void deleteUser() throws Exception {

    }

}