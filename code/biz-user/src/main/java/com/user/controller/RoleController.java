package com.user.controller;

import com.user.comm.exception.JsonParseException;
import com.user.comm.result.Result;
import com.user.comm.result.ResultBuilder;
import com.user.domain.entity.Role;
import com.user.service.RoleInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {

    @Autowired
    private RoleInfoService roleInfoService;

    //input: role, description
    //output: list of all roles
    //add prepersist in entity to control default value
    @ApiOperation("Add new role")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role", value = "length<90", dataType = "string"),
            @ApiImplicitParam(name = "description", value = "role_description", dataType = "text")
    })
    @RequestMapping(value = "/role",method = RequestMethod.POST)
    public Result addRole(@RequestBody Role role) throws Exception {
        if (null == role) {
            throw new JsonParseException("role");
        }
        Page<Role> roleList = roleInfoService.createRole(role);

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(roleList)
                .build();
    }

    //input: id of the role
    //output: details includes id
    @ApiOperation("Get details of one role")
    @ApiImplicitParam(name = "roleId", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "role/{roleId}", method = RequestMethod.GET)
    public Result getRoleDetails(@PathVariable Long roleId) throws Exception {
        if (null == roleId) {
            throw new JsonParseException("roleId");
        }
        Role role = roleInfoService.findRoleById(roleId);

        ResultBuilder resultBuilder = new ResultBuilder().setCode(ResultBuilder.SUCCESS);
        if (null == role) {
            resultBuilder.setMessage("role not existed");
        } else {
            resultBuilder.setData(role);
        }

        return resultBuilder.build();
    }

    //no input
    //output: list of all roles
    //support paging func
    @ApiOperation("Get list of all roles by page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "page", dataType = "int", paramType = "param"),
            @ApiImplicitParam(name = "size", value = "size", dataType = "int", paramType = "param")
    })
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public Result getRoleList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<Role> roleList = roleInfoService.findAllRolesByPage(new PageRequest(page, size));

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(roleList)
                .build();
    }

    //input role, description, is_avail
    //output : the role after update
    @ApiOperation("update details of roles")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "role", value = "length<90", dataType = "string"),
            @ApiImplicitParam(name = "description", value = "role_description", dataType = "text"),
            @ApiImplicitParam(name = "is_available",value = "true, false", dataType = "boolean")
    })
    @RequestMapping(value = "role/{roleId}", method = RequestMethod.PUT)
    public Result updateRoleDetails(@PathVariable Long roleId, @RequestBody Role role) throws Exception {
        if (null == roleId) {
            throw new JsonParseException("roleId");
        }
        if (null == role) {
            throw new JsonParseException("role");
        }
        Role updatedRole = roleInfoService.updateRole(roleId, role);

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(updatedRole)
                .build();
    }

    //return a new list
    //actually: we dont delete the record actually, change is_avail status
    //output : return new list
    @ApiOperation("Delete one role")
    @ApiImplicitParam(name = "roleId", required = true, dataType = "int")
    @RequestMapping(value = "role/{roleId}", method = RequestMethod.DELETE)
    public Result deleteRole(@PathVariable Long roleId) throws Exception{
        if (null == roleId) {
            throw new JsonParseException("roleId");
        }
        Page<Role> roleList = roleInfoService.changeIsAvailableStatus(roleId, false);

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(roleList)
                .build();
    }

    // do it later
    public Result addPermissionsForRole() {
        return null;
    }
    
}
