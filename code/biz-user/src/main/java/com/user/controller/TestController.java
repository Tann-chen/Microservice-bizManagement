package com.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TokenStore tokenStore;

    @RequestMapping(method = RequestMethod.GET)
    public String testFunc(){
        return "hello, user!";
    }

    @RequestMapping(value = "/get_current", method = RequestMethod.GET)
    public Object getCurrentUser() {
        Object current =  SecurityContextHolder.getContext().getAuthentication();
        return current;
    }

    @PreAuthorize("hasAuthority('user_read')")
    @RequestMapping(value = "/get_success", method = RequestMethod.GET)
    public String testMethodSecuritySuccess() {
        return "success";
    }

    @PreAuthorize("hasAuthority('user_reload')")
    @RequestMapping(value = "/get_refuse", method = RequestMethod.GET)
    public String testMethodSecurityRefuse() {
        return "refuse";
    }



    @RequestMapping(value = "/get_extra", method = RequestMethod.GET)
    public Map<String, Object> getExtrInfo(OAuth2Authentication auth) {
        final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
        final OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
        System.out.println(accessToken);
        return accessToken.getAdditionalInformation();
    }

}
