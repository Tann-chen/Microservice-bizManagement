package com.user.controller;

import com.user.comm.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RoleController {

    //input: role, description
    //output: list of all roles
    //add prepersist in entity to control default value
    public Result addRole() {
        return null;
    }

    //input: id of the role
    //output: details includes id
    public Result getRoleDetails() {
        return null;
    }

    //no input
    //output: list of all roles
    //support paging func
    public Result getRoleList(){
        return null;
    }

    //input role, description, is_avail
    //output : the role after update
    public Result updateRoleDetails() {
        return null;
    }

    //return a new list
    //actually: we dont delete the record actually, change is_avail status
    //output : return new list
    public Result deleteRole(){
        return null;
    }

    // do it later
    public Result addPermissionsForRole() {
        return null;
    }
    
}
