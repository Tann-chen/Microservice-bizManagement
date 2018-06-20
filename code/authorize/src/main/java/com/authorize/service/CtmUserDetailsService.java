package com.authorize.service;

import com.authorize.entity.Permission;
import com.authorize.entity.Role;
import com.authorize.entity.User;
import com.authorize.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class CtmUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.queryByEmail(email);
        if (null == user) {
            throw new UsernameNotFoundException("User not existed");
        }

        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getIsActive(),
                true,
                true,
                user.getIsLocked(),
                getGrantedAuthorities(user)
        );

        return userDetails;
    }


    private Set<GrantedAuthority> getGrantedAuthorities(User user) {
        //use set to make permission unique
        Set<String> authorities = new HashSet<>();

        List<Role> roleList = user.getRoleList();
        for (Role role : roleList) {
            if (role.getIsAvailable()) {
                List<Permission> permissionList = role.getPermissionList();
                for (Permission permission : permissionList) {
                    if (permission.getIsAvailable()) {
                        authorities.add(permission.toString());
                    }
                }
            }
        }

        //direct mapping
        List<Permission> permissionList = user.getPermissionList();
        for (Permission permission : permissionList) {
            if (permission.getIsAvailable()) {
                authorities.add(permission.toString());
            }
        }

        //Cast String to GrantedAuthority
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (String auth : authorities) {
            GrantedAuthority authority = new SimpleGrantedAuthority(auth);
            grantedAuthorities.add(authority);
        }

        return grantedAuthorities;
    }

}

