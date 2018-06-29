package com.user.repository;

import com.user.domain.entity.Module;
import com.user.domain.entity.Permission;
import com.user.domain.enums.PermissionType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface PermissionRepository extends CrudRepository<Permission, Long> {

    Optional<Permission> queryById(Long permissionId);

    Permission queryByModuleAndPermission(Module module, PermissionType type);

    List<Permission> queryByModule(Module module);
}
