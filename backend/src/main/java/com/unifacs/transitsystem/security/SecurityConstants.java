package com.unifacs.transitsystem.security;

public interface SecurityConstants {
    String SECRET = "SECRET_KEY";
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
    String SIGN_UP_URL = "/auth/login";
    long EXPIRATION_TIME = 3_600_000; // 1 hour
}
