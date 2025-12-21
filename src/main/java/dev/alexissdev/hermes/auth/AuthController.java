package dev.alexissdev.hermes.auth;

import dev.alexissdev.hermes.auth.credentials.ServerCredentials;
import dev.alexissdev.hermes.auth.login.LoginResponse;
import dev.alexissdev.hermes.auth.refresh.RefreshTokenRequest;
import dev.alexissdev.hermes.server.checker.ServerCredentialsChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static dev.alexissdev.hermes.secutiry.configuration.SecurityTokenConfiguration.REFRESH_TOKEN_EXPIRATION_TIME;
import static dev.alexissdev.hermes.util.TokenUtil.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final ServerCredentialsChecker credentialsChecker;

    @Autowired
    public AuthController(ServerCredentialsChecker credentialsChecker) {
        this.credentialsChecker = credentialsChecker;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody ServerCredentials serverCredentials) {
        if (!credentialsChecker.check(serverCredentials)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String serverId = serverCredentials.serverId();
        return ResponseEntity.ok(new LoginResponse(
                generateToken(serverId),
                generateToken(serverId, REFRESH_TOKEN_EXPIRATION_TIME)
        ));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@RequestBody RefreshTokenRequest request) {
        String refreshToken = request.refreshToken();
        if (refreshToken == null || !validateToken(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String serverId = getUserIdFromToken(refreshToken);
        return ResponseEntity.ok(new LoginResponse(
                generateToken(serverId),
                generateToken(serverId, REFRESH_TOKEN_EXPIRATION_TIME)
        ));
    }
}
