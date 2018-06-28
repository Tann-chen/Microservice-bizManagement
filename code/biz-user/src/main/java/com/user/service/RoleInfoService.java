package com.user.service;

import com.user.comm.result.ModulePermissions;
import com.user.domain.entity.Module;
import com.user.domain.entity.Permission;
import com.user.domain.entity.Role;

import java.util.List;


public interface RoleInfoService {

    void addRole(Role role);

    Role getRoleById(Long roleId);

    Role updateRole(Long roleId, Role updatedRoleInfo);

    void changeIsAvailableStatus(Long roleId, boolean isAvailable);

    List<Role> findAllRolesByPage();

    List<Permission> findPermissionsByRole(Role role);

    ModulePermissions findModulePermissionsByRole(Role role, Module module);
}
