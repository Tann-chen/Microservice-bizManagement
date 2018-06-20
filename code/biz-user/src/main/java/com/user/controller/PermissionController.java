package com.user.controller;


import com.user.comm.result.ModulePermissions;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PermissionController {

    //requestMethod = OPTION
    public List<String> getModules() {
        return null;
    }


    public Map<String, ModulePermissions> updatePermissions(Long roleId , Map<String, ModulePermissions> p){
        return null;
    }


    public Map<String, ModulePermissions> getPermissions(Long roleId){
        return null;
    }


}
