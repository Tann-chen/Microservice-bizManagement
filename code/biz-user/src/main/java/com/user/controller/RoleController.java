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
        roleInfoService.addRole(role);
        Page<Role> roleList = roleInfoService.findAllRolesByPage(new PageRequest(0, 10));

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(roleList)
                .build();
    }


    @ApiOperation("Get details of one role")
    @ApiImplicitParam(name = "roleId", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "role/{roleId}", method = RequestMethod.GET)
    public Result getRoleDetails(@PathVariable Long roleId) throws Exception {
        if (null == roleId) {
            throw new JsonParseException("roleId");
        }
        Role role = roleInfoService.getRoleById(roleId);

        ResultBuilder resultBuilder = new ResultBuilder().setCode(ResultBuilder.SUCCESS);
        if (null == role) {
            resultBuilder.setMessage("role not existed");
        } else {
            resultBuilder.setData(role);
        }

        return resultBuilder.build();
    }


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


    @ApiOperation("Delete one role")
    @ApiImplicitParam(name = "roleId", required = true, dataType = "int")
    @RequestMapping(value = "role/{roleId}", method = RequestMethod.DELETE)
    public Result deleteRole(@PathVariable Long roleId) throws Exception{
        if (null == roleId) {
            throw new JsonParseException("roleId");
        }
        roleInfoService.changeIsAvailableStatus(roleId, false);
        Page<Role> roleList = roleInfoService.findAllRolesByPage(new PageRequest(0, 10));

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(roleList)
                .build();
    }


    public Result addPermissionsForRole() {
        return null;
    }

}
