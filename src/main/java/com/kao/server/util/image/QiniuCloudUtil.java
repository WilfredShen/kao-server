package com.kao.server.util.image;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.util.properties.QiniuCloudProperties;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 沈伟峰
 */
@Component
public class QiniuCloudUtil {

    private static QiniuCloudUtil qiniuCloudUtil;
    private static Auth auth;
    private static UploadManager uploadManager;

    @Autowired
    private QiniuCloudProperties properties;

    public static String upload(byte[] bytes, String key) {
        String uploadToken = auth.uploadToken(qiniuCloudUtil.properties.bucketName);
        String res = null;
        try {
            Response response = uploadManager.put(bytes, key, uploadToken);
            JSONObject jsonObject = JSONObject.parseObject(response.bodyString());
            res = jsonObject.getString("key");
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String packUrl(String filename) {
        return "http://" + qiniuCloudUtil.properties.domain + "/" + filename;
    }

    @PostConstruct
    public void init() {
        qiniuCloudUtil = this;
        qiniuCloudUtil.properties = this.properties;
        auth = Auth.create(qiniuCloudUtil.properties.accessKey, qiniuCloudUtil.properties.secretKey);
        try {
            Method method = Region.class.getMethod(qiniuCloudUtil.properties.region);
            try {
                Region region = (Region) method.invoke(null);
                Configuration cfg = new Configuration(region);
                uploadManager = new UploadManager(cfg);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
