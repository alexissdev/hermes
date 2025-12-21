package dev.alexissdev.hermes.secutiry.configuration;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

public class SecurityTokenConfiguration {

    public static final long EXPIRATION_TIME = 3600_000;
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

}
