package com.user.service;

import com.user.domain.entity.Module;
import com.user.domain.enums.PermissionType;

public interface PermissionService {

    boolean isExistedPermission(Module module, PermissionType type);

    void addPermission(Module module, PermissionType type);

}
