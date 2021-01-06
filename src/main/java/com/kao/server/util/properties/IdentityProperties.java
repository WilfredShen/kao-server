package com.kao.server.util.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author 沈伟峰
 */
@Component
public class IdentityProperties {

    @Value("${identity.api}")
    public String api;

    @Value("${identity.secret-id}")
    public String secretId;

    @Value("${identity.secret-key}")
    public String secretKey;

    @Value("${identity.source}")
    public String source;

    @Value("${identity.request-method}")
    public String method;
}
