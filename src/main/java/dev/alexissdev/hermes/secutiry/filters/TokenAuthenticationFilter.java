package dev.alexissdev.hermes.secutiry.filters;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

import static dev.alexissdev.hermes.secutiry.configuration.SecurityTokenConfiguration.*;
import static dev.alexissdev.hermes.secutiry.configuration.SecurityTokenConfiguration.TOKEN_PREFIX;
import static dev.alexissdev.hermes.util.TokenUtil.*;

public class TokenAuthenticationFilter
        extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader(HEADER_STRING);
        String tokenPrefix = TOKEN_PREFIX;

        if (authHeader != null && authHeader.startsWith(tokenPrefix)) {
            String token = authHeader.substring(tokenPrefix.length());
            try {
                if (validateToken(token)) {
                    String userId = getUserIdFromToken(token);
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            userId,
                            null,
                            Collections.emptyList()
                    );

                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception ignored) {}
        }

        filterChain.doFilter(request, response);
    }
}
