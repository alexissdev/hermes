package dev.alexissdev.hermes.secutiry.configuration;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

/**
 * SecurityTokenConfiguration defines the constants and key configuration
 * used for managing security tokens in the application.
 *
 * This class includes token expiration time, a secret key for signing,
 * and standard header information required for handling authorization tokens.
 * It is designed to centralize the token-related configurations
 * and ensure consistency across the application.
 */

public class SecurityTokenConfiguration {

    public static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;
    public static final long REFRESH_TOKEN_EXPIRATION_TIME = EXPIRATION_TIME * 7;
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

}
