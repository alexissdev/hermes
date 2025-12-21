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

    public static final long EXPIRATION_TIME = 3600_000;
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

}
