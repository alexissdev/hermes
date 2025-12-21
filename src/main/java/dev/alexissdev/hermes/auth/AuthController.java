package dev.alexissdev.hermes.auth;

import dev.alexissdev.hermes.auth.credentials.ServerCredentials;
import dev.alexissdev.hermes.server.checker.ServerCredentialsChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static dev.alexissdev.hermes.util.TokenUtil.generateToken;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final ServerCredentialsChecker credentialsChecker;

    @Autowired
    public AuthController(ServerCredentialsChecker credentialsChecker) {
        this.credentialsChecker = credentialsChecker;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody ServerCredentials serverCredentials) {
        if (!credentialsChecker.check(serverCredentials)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(generateToken(serverCredentials.serverId()));
    }
}
