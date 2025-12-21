package dev.alexissdev.hermes.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import java.util.Date;

import static dev.alexissdev.hermes.secutiry.configuration.SecurityTokenConfiguration.*;

public class TokenUtil {

    public static String generateToken(String userId) {
        return Jwts.builder()
                .subject(userId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token);

            return true;
        } catch (JwtException ex) {
            return false;
        }
    }

    public static String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }
}