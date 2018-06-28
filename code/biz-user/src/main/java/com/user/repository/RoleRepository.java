package com.user.repository;


import com.user.domain.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role queryRoleByRole(String role);

    Role queryRoleById(Long roleId);

    List<Role> findByIsAvailableTrue();
}
