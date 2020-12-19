package com.kao.server.util.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author 沈伟峰
 */
@Component
@PropertySource(value = {"classpath:application-qiniu.yml"})
public class QiniuCloudProperties {

    @Value("${qiniu.domain}")
    public String domain;

    @Value("${qiniu.access-key}")
    public String accessKey;

    @Value("${qiniu.secret-key}")
    public String secretKey;

    @Value("${qiniu.bucket-name}")
    public String bucketName;

    @Value("${qiniu.region}")
    public String region;
}
