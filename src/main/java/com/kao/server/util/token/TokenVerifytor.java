package com.kao.server.util.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kao.server.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 全鸿润
 */
public class TokenVerifytor {
    @Autowired
    static UserServiceImpl userService;

    public static boolean verifyToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(TokenContant.getSecretKey());
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT jwt = jwtVerifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    private static DecodedJWT getDecodeJwt(String token) {
        Algorithm algorithm = Algorithm.HMAC256(TokenContant.getSecretKey());
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        return jwt;
    }

    public static String getUserNameFromToken(String token) {
        return getDecodeJwt(token).getClaim("username").asString();
    }

    public static String getPasswordFromToken(String token) {
        return getDecodeJwt(token).getClaim("password").asString();
    }

    public static String getAccountTypeFromToken(String token) {
        return getDecodeJwt(token).getClaim("accountType").asString();
    }

    public static int getUidFromToken(String token) {
        return getDecodeJwt(token).getClaim("uid").asInt();
    }
}
