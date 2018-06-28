package com.user.controller;

import com.user.comm.exception.JsonParseException;
import com.user.comm.result.ModulePermissions;
import com.user.comm.result.Result;
import com.user.comm.result.ResultBuilder;
import com.user.domain.entity.Module;
import com.user.domain.entity.Role;
import com.user.service.ModuleService;
import com.user.service.RoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleInfoService roleInfoService;

    @Autowired
    private ModuleService moduleService;


    @RequestMapping(method = RequestMethod.POST)
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


    @RequestMapping(value = "/{roleId}", method = RequestMethod.GET)
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


    @RequestMapping(method = RequestMethod.GET)
    public Result getRoleList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<Role> roleList = roleInfoService.findAllRolesByPage(new PageRequest(page, size));
        Role firstRole = roleList.getContent().get(1);
        List<Module> moduleList = moduleService.getAvailModulesByAdmin();
        Map<String, ModulePermissions> mpMap = new HashMap<>();

        for(Module m : moduleList){
            ModulePermissions mp = roleInfoService.findModulePermissionsByRole(firstRole, m);
            mpMap.put(m.getName(), mp);
        }

        Map<String, Object> result= new HashMap<>();
        result.put("role_list", roleList);
        result.put("module_list", moduleList);
        result.put("first_role_permissions", mpMap);

        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(result)
                .build();
    }


    @RequestMapping(value = "/{roleId}", method = RequestMethod.PUT)
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


    @RequestMapping(value = "/{roleId}", method = RequestMethod.DELETE)
    public Result deleteRole(@PathVariable Long roleId) throws Exception {
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
