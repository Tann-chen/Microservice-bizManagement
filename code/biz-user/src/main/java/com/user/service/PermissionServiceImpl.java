package com.user.service;

import com.user.domain.entity.Module;
import com.user.domain.entity.Permission;
import com.user.domain.enums.PermissionType;
import com.user.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public boolean isExistedPermission(Module module, PermissionType type) {
        Permission p = permissionRepository.queryByModuleAndPermission(module, type);
        return null == p;
    }

    @Override
    public void addPermission(Module module, PermissionType type) {
        Permission permission = null;
        if (!isExistedPermission(module, type)) {
            Permission p = new Permission();
            p.setModule(module);
            p.setPermission(type);
            permission = permissionRepository.save(p);

        }
    }
}
