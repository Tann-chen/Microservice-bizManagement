package com.user.service;

import com.user.comm.result.ModulePermissions;
import com.user.domain.entity.Module;
import com.user.domain.entity.Permission;
import com.user.domain.entity.Role;
import com.user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class RoleInfoServiceImpl implements RoleInfoService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void addRole(Role role) throws IllegalArgumentException{
        Assert.hasLength(role.getRole(), "role name not empty");
        Role existing = roleRepository.queryRoleByRole(role.getRole());
        Assert.isNull(existing, "role already exist");
        Assert.hasLength(role.getDescription(), "description not empty");
        roleRepository.save(role);
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleRepository.queryRoleById(roleId);
    }

    @Override
    public Role updateRole(Long roleId, Role updatedRoleInfo) throws IllegalArgumentException{
        Role role = roleRepository.queryRoleById(roleId);
        Assert.notNull(role, "role not existed");
        if (!StringUtils.isEmpty(updatedRoleInfo.getRole())) {
            role.setRole(updatedRoleInfo.getRole());
        }
        if (!StringUtils.isEmpty(updatedRoleInfo.getDescription())) {
            role.setDescription(updatedRoleInfo.getDescription());
        }
        if (!StringUtils.isEmpty(updatedRoleInfo.getIsAvailable())) {
            role.setIsAvailable(updatedRoleInfo.getIsAvailable());
        }
        roleRepository.save(role);

        return role;
    }

    @Override
    public void changeIsAvailableStatus(Long roleId, boolean isAvailable) throws IllegalArgumentException {
        Role role = roleRepository.queryRoleById(roleId);
        Assert.notNull(role, "role not existed");
        role.setIsAvailable(isAvailable);
        roleRepository.save(role);
    }

    @Override
    public Page<Role> findAllRolesByPage(Pageable pageable) {
        return roleRepository.findByIsAvailableTrue(pageable);
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
