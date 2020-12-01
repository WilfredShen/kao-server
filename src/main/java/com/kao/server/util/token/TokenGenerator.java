package com.kao.server.util.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 全鸿润
 */
public class TokenGenerator {

    /**
     * 根据传入的用户名和用户ID生成token
     * 这里使用的是HS256加密算法
     */
    public static String generateToken(String username, String userid, String password, String accountType) {

        Date date = new Date(System.currentTimeMillis() + TokenConstant.getExpiredTime());
        Algorithm algorithm = Algorithm.HMAC256(TokenConstant.getSecretKey());
        Map<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        return JWT.create()
                .withHeader(header)
                .withClaim("uid", userid)
                .withClaim("username", username)
                .withClaim("password", password)
                .withClaim("accountType", accountType)
                .withExpiresAt(date)
                .sign(algorithm);
    }


}
