package com.user.service;

import com.user.domain.entity.Role;
import com.user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Service
public class RoleInfoServiceImpl implements RoleInfoService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Page<Role> createRole(Role role) throws IllegalArgumentException{
        //todo: it is not good to define func with not single func. bad for request change, please check all func defined in this class
        Assert.hasLength(role.getRole(), "role name not empty");
        Role existing = roleRepository.queryRoleByRole(role.getRole());
        Assert.isNull(existing, "role already exist");
        Assert.hasLength(role.getDescription(), "description not empty");
        roleRepository.save(role);
        Page<Role> roleList = roleRepository.findByIsAvailableTrue(new PageRequest(0, 10));

        return roleList;
    }

    @Override
    public Role findRoleById(Long roleId) {
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
    public Page<Role> changeIsAvailableStatus(Long roleId, boolean isAvailable) throws IllegalArgumentException{
        Role role = roleRepository.queryRoleById(roleId);
        Assert.notNull(role, "role not existed");
        role.setIsAvailable(isAvailable);
        roleRepository.save(role);
        Page<Role> roleList = roleRepository.findByIsAvailableTrue(new PageRequest(0, 10));

        return roleList;
    }

    @Override
    public Page<Role> findAllRolesByPage(Pageable pageable) {
        return roleRepository.findByIsAvailableTrue(pageable);
    }
}
