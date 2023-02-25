package com.aro.javaadmin.security;

import java.util.concurrent.TimeUnit;

public class JWTUtil {

    public static final long EXPIRE_ACCESS_TOKEN = TimeUnit.MINUTES.toNanos(10);

    public static final long EXPIRE_REFRESH_TOKEN = TimeUnit.MINUTES.toNanos(1000);

    public static final String ISSUER = "springBootApp";

    public static final String BEARER_PREFIX = "Bearer ";

    public static final String SECRET = "myPrivateSecret";

    public static final String AUTH_HEADER = "Authorization";

}
