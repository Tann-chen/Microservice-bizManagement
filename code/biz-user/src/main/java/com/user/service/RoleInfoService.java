package com.user.service;

import com.user.domain.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface RoleInfoService {

    void addRole(Role role);

    Role getRoleById(Long roleId);

    Role updateRole(Long roleId, Role updatedRoleInfo);

    void changeIsAvailableStatus(Long roleId, boolean isAvailable);

    Page<Role> findAllRolesByPage(Pageable pageable);
}
