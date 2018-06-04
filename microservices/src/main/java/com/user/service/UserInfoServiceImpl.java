package com.user.service;

import com.user.comm.exception.BizException;
import com.user.comm.exception.BizExceptionEnum;
import com.user.comm.exception.SysExceptionEnum;
import com.user.comm.exception.SystemException;
import com.user.domain.entity.Permission;
import com.user.domain.entity.User;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Long createUser(User user) throws RuntimeException {
        if (null == user) {
            throw new SystemException(SysExceptionEnum.PARAM_NOT_NULL);
        }
        String email = user.getEmail();
        User existedUser = userRepository.queryUserByEmail(email);
        if (null != existedUser) {
            throw new BizException(BizExceptionEnum.EMAIL_ALREADY_EXIST);
        }
        User savedUser = userRepository.saveAndFlush(user);
        return savedUser.getId();
    }

    @Override
    public User findUserByEmail(String email) throws RuntimeException {
        if (null == email) {
            throw new SystemException(SysExceptionEnum.PARAM_NOT_NULL);
        }
        return userRepository.queryUserByEmail(email);
    }

    @Override
    public User findUserById(Long userId) throws RuntimeException {
        if (null == userId) {
            throw new SystemException(SysExceptionEnum.PARAM_NOT_NULL);
        }
        return userRepository.queryUserById(userId);
    }

    @Override
    public void updateUser(Long userId, User updatedUserInfo) throws RuntimeException {
        if (null == userId || null == updatedUserInfo) {
            throw new SystemException(SysExceptionEnum.PARAM_NOT_NULL);
        }
        User user = (User) updatedUserInfo.clone();
        user.setId(userId);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(Long userId) {
        if (null == userId) {
            throw new SystemException(SysExceptionEnum.PARAM_NOT_NULL);
        }
        userRepository.delete(userId);
    }

    @Override
    public boolean credentialCheck(User userInfo) {
        if (null == userInfo) {
            throw new SystemException(SysExceptionEnum.PARAM_NOT_NULL);
        }

        String inputPass = userInfo.getPassword();
        User user = userRepository.queryUserByEmail(userInfo.getEmail());
        String realPass = user.getPassword();
        return inputPass.equals(realPass);
    }
}
