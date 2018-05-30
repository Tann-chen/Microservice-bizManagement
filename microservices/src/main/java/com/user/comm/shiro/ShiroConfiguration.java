package com.user.comm.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //set security manager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //set the route of login page
        shiroFilterFactoryBean.setLoginUrl("/login");
        //set the route of main page after passing auth
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //set the route of failed_auth page
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        //---------------- define filter chains -----------------
        Map<String,String> filterChainDefinition = new HashMap<>();
        //Shiro has implemented function of logout
        filterChainDefinition.put("/logout", "logout");
        //authc:request auth for all url ; anon: not request auth for all url
        filterChainDefinition.put("/**", "authc");
        //-------------------------------------------------------

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinition);
        return shiroFilterFactoryBean;
    }


    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        return securityManager;
    }

}
