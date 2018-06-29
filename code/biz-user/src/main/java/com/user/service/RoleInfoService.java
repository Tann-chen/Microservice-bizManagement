package com.user.service;

import com.user.comm.result.ModulePermissions;
import com.user.domain.entity.Module;
import com.user.domain.entity.Permission;
import com.user.domain.entity.Role;

import java.util.List;
import java.util.Optional;


public interface RoleInfoService {

    void addRole(Role role);

    Optional<Role> getRoleById(Long roleId);

    Role updateRole(Long roleId, Role updatedRoleInfo);

    void changeIsAvailableStatus(Long roleId, boolean isAvailable);

    List<Role> findAllRoles();

    List<Permission> findPermissionsByRole(Role role);

    ModulePermissions findModulePermissionsByRole(Role role, Module module);
}
