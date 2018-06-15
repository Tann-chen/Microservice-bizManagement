package com.user.service;

import com.user.domain.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface RoleInfoService {

    Page<Role> createRole(Role role);

    Role findRoleById(Long roleId);

    Role updateRole(Long roleId, Role updatedRoleInfo);

    Page<Role> changeIsAvailableStatus(Long roleId, boolean isAvailable);

    Page<Role> findAllRolesByPage(Pageable pageable);
}
