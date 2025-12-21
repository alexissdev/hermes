package dev.alexissdev.hermes.auth.login;

/**
 * Represents a response for a login request.
 * This record encapsulates the authentication tokens provided to the client after a successful login.
 *
 * @param accessToken  The token used for accessing protected resources. Typically has a short lifespan.
 * @param refreshToken The token used for obtaining a new access token when the current one expires. Usually has a longer lifespan.
 */

public record LoginResponse(String accessToken, String refreshToken) {
}
