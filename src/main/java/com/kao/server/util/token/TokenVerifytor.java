package com.kao.server.util.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * @author 全鸿润
 */
public class TokenVerifytor {

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
}
