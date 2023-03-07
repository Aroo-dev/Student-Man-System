package com.aro.javaadmin.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;

import java.time.Clock;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Configuration
public class JWTHelper {
    private static final long EXPIRE_ACCESS_TOKEN = TimeUnit.MINUTES.toNanos(10);

    private static final long EXPIRE_REFRESH_TOKEN = TimeUnit.MINUTES.toNanos(1000);

    private static final String ISSUER = "springBootApp";

    private static final String BEARER_PREFIX = "Bearer ";

    public static final String SECRET = "myPrivateSecret";

    public static final String AUTH_HEADER = "Authorization";

    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET);

    private static final String ROLES = "roles";


    public String generateAccessToken(String email, List<String> roles){
        return JWT.create()
                .withSubject(email)
                .withExpiresAt( Instant.now().plusNanos(EXPIRE_ACCESS_TOKEN))
                .withIssuer(ISSUER)
                .withClaim(ROLES,roles)
                .sign(algorithm);
    }

    public String generateRefreshToken(String email){
        return JWT.create().withSubject(email)
                .withExpiresAt(Instant.now().plusSeconds(EXPIRE_REFRESH_TOKEN))
                .withIssuer(ISSUER)
                .sign(algorithm);
    }

    public String extractTokenFromHeaderIfExist(String authorizationHeader){
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)){
            return authorizationHeader.substring(BEARER_PREFIX.length());

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
