package com.kao.server.util.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * @author 全鸿润
 */
public class TokenVerifier {

    /**
     * 解析token
     *
     * @param token 密钥
     * @return 解析结果
     */
    public static boolean verifyToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(TokenConstant.getSecretKey());
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT jwt = jwtVerifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获取DecodeJwt对象
     *
     * @param token 密钥
     * @return DecodeJwt对象
     */
    private static DecodedJWT getDecodeJwt(String token) {
        Algorithm algorithm = Algorithm.HMAC256(TokenConstant.getSecretKey());
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        return jwtVerifier.verify(token);
    }

    /**
     * 获取token中的用户名
     *
     * @param token 密钥
     * @return 用户名
     */
    public static String getUserNameFromToken(String token) {
        return getDecodeJwt(token).getClaim("username").asString();
    }

    /**
     * 获取token中的密码
     *
     * @param token 密钥
     * @return 密码
     */
    public static String getPasswordFromToken(String token) {
        return getDecodeJwt(token).getClaim("password").asString();
    }

    public static String getUserIdFromToken(String token) {
        return getDecodeJwt(token).getClaim("uid").asString();
    }

}
