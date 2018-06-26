package com.authorize.service;

import com.authorize.entity.User;


public class DataHolderService {

    private static ThreadLocal<User> CURRENT_USER = new ThreadLocal<User>();

    private DataHolderService(){}

    public static User getCurrentUser() {
        return CURRENT_USER.get();
    }

    public static void setCurrentUser(User currentUser) {
        CURRENT_USER.set(currentUser);
    }

}
