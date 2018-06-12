package com.user.comm.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
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

        //define filter chains
        Map<String,String> filterChainDefinition = new HashMap<>();
        //Shiro has implemented function of logout
        filterChainDefinition.put("/logout", "logout");
        //define url accessed without auth (anon: not request auth for the url)
        filterChainDefinition.put("/**", "anon");
        //define url accessed with auth (authc:request auth for the url)
        //filterChainDefinition.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinition);
        return shiroFilterFactoryBean;
    }


    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(1);
        //the bean will tell SimpleAuthenticationInfo how to deal with credentials
        return credentialsMatcher;
    }


    @Bean
    public ShiroRealmConfig shiroRealmConfig(){
        ShiroRealmConfig shiroRealmConfig = new ShiroRealmConfig();
        //config the credentialsMatcher in shiroRealmConfig
        shiroRealmConfig.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealmConfig;
    }


    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //config the shiroRealm into securityManager
        //by shiroRealmConfig, auth info will be accessed from database
        securityManager.setRealm(shiroRealmConfig());
        return securityManager;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        //config the use of annotation to define required permissions for functions
        return authorizationAttributeSourceAdvisor;
    }

}
