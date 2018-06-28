package com.user.repository;

import com.user.domain.entity.Module;
import com.user.domain.entity.Permission;
import com.user.domain.enums.PermissionType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PermissionRepository extends CrudRepository<Permission, Long> {

    Permission queryById(Long permissionId);

    Permission queryByModuleAndPermission(Module module, PermissionType type);

    List<Permission> queryByModule(Module module);
}
