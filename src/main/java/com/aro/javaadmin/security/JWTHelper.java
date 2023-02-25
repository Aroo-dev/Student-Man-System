package com.aro.javaadmin.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

@RequiredArgsConstructor
@Configuration
public class JWTHelper {

    private static final Algorithm algorithm = Algorithm.HMAC256(JWTUtil.SECRET);


    public String generateAccessToken(String email, List<String> roles){
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + JWTUtil.EXPIRE_ACCESS_TOKEN))
                .withIssuer(JWTUtil.ISSUER)
                .withClaim("roles",roles)
                .sign(algorithm);
    }

    public String generateRefreshToken(String email){
        return JWT.create().withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + JWTUtil.EXPIRE_REFRESH_TOKEN))
                .withIssuer(JWTUtil.ISSUER)
                .sign(algorithm);
    }

    public String extractTokenFromHeaderIfExist(String authorizationHeader){
        if (authorizationHeader != null && authorizationHeader.startsWith(JWTUtil.BEARER_PREFIX)){
            return authorizationHeader.substring(JWTUtil.BEARER_PREFIX.length());

        }
        return null;
    }

    public Map<String,String> getTokensMap(String jwtAccessToken, String jwtRefreshToken){
        Map<String, String> idToken = new HashMap<>();
        idToken.put("accessToken", jwtAccessToken);
        idToken.put("refreshToken", jwtRefreshToken);
        return idToken;
    }
}
