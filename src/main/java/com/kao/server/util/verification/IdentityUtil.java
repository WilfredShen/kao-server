package com.kao.server.util.verification;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.util.http.HttpUtil;
import com.kao.server.util.properties.IdentityProperties;
import com.kao.server.util.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 沈伟峰
 */
@Component
public class IdentityUtil {

    private static IdentityUtil identityUtil;

    @Autowired
    private IdentityProperties properties;

    /**
     * 生成验证信息
     *
     * @param source    获取 api 的源
     * @param secretId  密钥 id
     * @param secretKey 密钥 key
     * @param datetime  时间戳
     * @return 生成的验证信息
     */
    private static String createAuth(String source, String secretId, String secretKey, String datetime)
            throws NoSuchAlgorithmException, InvalidKeyException {
        String signString = "x-date: " + datetime + "\n" + "x-source: " + source;
        Mac mac = Mac.getInstance("HmacSHA1");
        Key sKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), mac.getAlgorithm());
        mac.init(sKey);
        byte[] hash = mac.doFinal(signString.getBytes(StandardCharsets.UTF_8));
        String sig = SecurityUtil.encodeBase64(hash);

        return "hmac id=\"" + secretId + "\", algorithm=\"hmac-sha1\", headers=\"x-date x-source\", signature=\"" + sig + "\"";
    }

    private static Map<Object, Object> parseResult(JSONObject result) {
        Map<Object, Object> map = new HashMap<>();

        boolean isOK = result.getBoolean("isok");
        map.put("isOK", isOK);
        String identity = result.getString("idcard");
        map.put("identity", identity);

        JSONObject idCardInfo = result.getJSONObject("IdCardInfor");
        String sex = idCardInfo.getString("sex");
        map.put("sex", "男".equals(sex) ? "M" : "F");
        Date birthday = idCardInfo.getSqlDate("birthday");
        map.put("birthday", birthday);

        return map;
    }

    /**
     * 进行实名认证
     *
     * @param identity 身份证号
     * @param name     真实姓名
     * @return 实名认证结果<br />
     * boolean isOK: 认证是否通过<br/>
     * String name: 脱敏姓名<br/>
     * String identity: 脱敏身份证号<br/>
     * String sex: "M"表示男性，"F"表示女性<br/>
     * java.sql.Date birthday: 生日
     */
    public static Map<Object, Object> verify(String identity, String name)
            throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        Map<Object, Object> map = null;

        String secretId = identityUtil.properties.secretId;
        String secretKey = identityUtil.properties.secretKey;
        String source = identityUtil.properties.source;
        String method = identityUtil.properties.method;
        String url = identityUtil.properties.api;

        Calendar cd = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String datetime = sdf.format(cd.getTime());
        String auth = createAuth(source, secretId, secretKey, datetime);

        Map<String, String> headers = new HashMap<>();
        headers.put("X-Source", source);
        headers.put("X-Date", datetime);
        headers.put("Authorization", auth);

        // 查询参数
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("cardNo", identity);
        queryParams.put("realName", name);

        // url参数拼接
        if (!queryParams.isEmpty()) {
            url += "?" + HttpUtil.packUrlParams(queryParams);
        }

        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod(method);

            // request headers
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            JSONObject jsonObject = JSONObject.parseObject(response.toString());
            int errorCode = jsonObject.getIntValue("error_code");
            System.out.println("error_code:" + errorCode);
            if (errorCode == 0) {
                JSONObject result = jsonObject.getJSONObject("result");
                map = parseResult(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return map;
    }

    @PostConstruct
    public void init() {
        identityUtil = this;
        identityUtil.properties = this.properties;
    }
}
