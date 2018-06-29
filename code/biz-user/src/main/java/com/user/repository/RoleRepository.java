package com.user.repository;


import com.user.domain.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> queryRoleByRole(String role);

    Optional<Role> queryRoleById(Long roleId);

    List<Role> findByIsAvailableTrue();
}
