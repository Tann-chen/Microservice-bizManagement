package com.user.service;

import com.user.comm.result.ModulePermissions;
import com.user.domain.entity.Module;
import com.user.domain.entity.Permission;
import com.user.domain.entity.Role;
import com.user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class RoleInfoServiceImpl implements RoleInfoService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void addRole(Role role) throws IllegalArgumentException {
        Assert.hasLength(role.getRole(), "role name not empty");
        Assert.hasLength(role.getDescription(), "description not empty");
        Optional<Role> existing = roleRepository.queryRoleByRole(role.getRole());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("role has already existed");
        }
        roleRepository.save(role);
    }

    @Override
    public Optional<Role> getRoleById(Long roleId) {
        return roleRepository.queryRoleById(roleId);
    }

    @Override
    public Role updateRole(Long roleId, Role updatedRoleInfo) throws IllegalArgumentException {
        Optional<Role> optionalRole = roleRepository.queryRoleById(roleId);
        Role role = optionalRole.orElseThrow(() -> new IllegalArgumentException("role not existed"));
        if (!StringUtils.isEmpty(updatedRoleInfo.getRole())) {
            role.setRole(updatedRoleInfo.getRole());
        }
        if (!StringUtils.isEmpty(updatedRoleInfo.getDescription())) {
            role.setDescription(updatedRoleInfo.getDescription());
        }
        if (!StringUtils.isEmpty(updatedRoleInfo.getIsAvailable())) {
            role.setIsAvailable(updatedRoleInfo.getIsAvailable());
        }

        Role newRole = roleRepository.save(role);
        return newRole;
    }

    @Override
    public void changeIsAvailableStatus(Long roleId, boolean isAvailable) throws IllegalArgumentException {
        Optional<Role> optionalRole = roleRepository.queryRoleById(roleId);
        Role role = optionalRole.orElseThrow(() -> new IllegalArgumentException("role not existed"));
        role.setIsAvailable(isAvailable);
        roleRepository.save(role);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findByIsAvailableTrue();
    }


    @Override
    public List<Permission> findPermissionsByRole(Role role) {
        return role.getPermissionList();
    }

    @Override
    public ModulePermissions findModulePermissionsByRole(Role role, Module module) {
        ModulePermissions mp = new ModulePermissions();
        List<Permission> permissionList = role.getPermissionList();
        for (Permission p : permissionList) {
            if (p.getModule().equals(module)) {
                mp.setFieldValue(p.getPermission().getType(), true);
            }
        }

        return mp;
    }
}
