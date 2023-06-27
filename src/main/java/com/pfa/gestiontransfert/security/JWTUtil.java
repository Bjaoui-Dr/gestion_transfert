package com.pfa.gestiontransfert.security;

public class JWTUtil {
    public static final String SECRET = "SECRET_KEY";
    public static final String AUTH_HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";
    // public static final long EXPIRE_ACCESS_TOKEN = 10 * 60 * 1000;
    public static final long EXPIRE_REFRESH_TOKEN = 30 * 24 * 60 * 60 * 1000L;
}
