package com.user.service;

import com.user.comm.result.ModulePermissions;
import com.user.domain.entity.Module;
import com.user.domain.entity.Permission;
import com.user.domain.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface RoleInfoService {

    void addRole(Role role);

    Role getRoleById(Long roleId);

    Role updateRole(Long roleId, Role updatedRoleInfo);

    void changeIsAvailableStatus(Long roleId, boolean isAvailable);

    Page<Role> findAllRolesByPage(Pageable pageable);

    List<Permission> findPermissionsByRole(Role role);

    ModulePermissions findModulePermissionsByRole(Role role, Module module);
}
