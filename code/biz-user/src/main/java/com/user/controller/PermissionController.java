package com.user.controller;


import com.user.comm.result.ModulePermission;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PermissionController {

    //requestMethod = OPTION
    public List<String> getModules() {
        return null;
    }


    public Map<String, ModulePermission> updatePermissions(Long roleId , Map<String, ModulePermission> p){
        return null;
    }


    public Map<String, ModulePermission> getPermissions(Long roleId){
        return null;
    }


}
