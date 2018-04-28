package com.tan.users.service;

import com.tan.users.comm.exception.BizException;
import com.tan.users.comm.exception.BizExceptionEnum;
import com.tan.users.domain.User;
import com.tan.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User newUser)throws BizException{
        String email = newUser.getEmail();
        User temp = userRepository.findUserByEmail(email);

        if(temp!= null){
            throw new BizException(BizExceptionEnum.EMAIL_ALREADY_EXIST);
        }

        userRepository.saveAndFlush(newUser);
    }


    public void deleteUser(User targetUser)throws BizException{
        int userId = targetUser.getId();
        User target = userRepository.findUserById(userId);

        if(target == null){
            throw new BizException(BizExceptionEnum.OBJECT_NOT_EXIST);
        }

        userRepository.delete(target);
    }
}
