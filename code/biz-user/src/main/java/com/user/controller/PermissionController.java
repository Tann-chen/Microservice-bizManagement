package com.user.controller;


import com.user.comm.result.Result;
import com.user.comm.result.ResultBuilder;
import com.user.domain.entity.Module;
import com.user.domain.entity.User;
import com.user.service.ModuleService;
import com.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/module")
@CrossOrigin(origins = "http://localhost:8080")
public class PermissionController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private TokenStore tokenStore;

    @RequestMapping(value = "/admin", method = RequestMethod.OPTIONS)
    public Result getModulesByAdmin() {
        List<Module> moduleList = moduleService.getAvailModulesByAdmin();
        return new ResultBuilder()
                .setCode(ResultBuilder.SUCCESS)
                .setData(moduleList)
                .build();
    }

    @RequestMapping(value = "/user", method = RequestMethod.OPTIONS)
    public Result getModulesByUser(OAuth2Authentication auth) {

        final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
        final OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
        Map<String, Object> tokenDetails = accessToken.getAdditionalInformation();
        Long currentUserId = (Long) tokenDetails.get("user_id");

        Optional<User> optionalUser = userInfoService.findUserById(currentUserId);

        ResultBuilder rb = new ResultBuilder();

        if (!optionalUser.isPresent()) {
            rb.setCode(ResultBuilder.FAILED).setMessage("user info missing");
        } else {
            Set<Module> moduleList = moduleService.getAvailModulesByUser(optionalUser.get());
            rb.setCode(ResultBuilder.SUCCESS).setData(moduleList);
        }

        return rb.build();
    }
}
