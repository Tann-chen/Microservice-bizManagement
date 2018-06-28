package com.user.repository;

import com.user.domain.entity.Permission;
import com.user.domain.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RoleRepository extends PagingAndSortingRepository<Role,Long> {

    Role queryRoleByRole(String role);

    Role queryRoleById(Long roleId);

    Page<Role> findByIsAvailableTrue(Pageable pageable);
}
