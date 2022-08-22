package com.memorybox.repository;

import java.util.Map;

public interface SocialOauth {
    String getOauthRedirectURL();
    Map<String, Object> getAttributes();
    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();
}
