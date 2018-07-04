package com.authorize.config;

import com.authorize.entity.User;
import static com.authorize.service.DataHolderService.getCurrentUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CtmTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication oAuth2Authentication) {
        final Map<String, Object> additional = new HashMap<>();
        User loginUser = getCurrentUser();
        additional.put("user_id", loginUser.getId());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additional);
        return accessToken;
    }
}
