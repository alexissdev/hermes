package dev.alexissdev.hermes.server.checker;

import dev.alexissdev.hermes.auth.credentials.ServerCredentials;
import dev.alexissdev.hermes.server.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Component responsible for validating server credentials against stored
 * server data in the repository.
 * <p>
 * This class leverages the {@link ServerRepository} to verify the credentials, ensuring
 * that the server ID and password provided match an existing server's information.
 * <p>
 * Annotations:
 * - @Component: Indicates that this class is a Spring-managed component.
 * - @Autowired: Injects the {@link ServerRepository} dependency.
 */

@Component
public class ServerCredentialsChecker {

    private final ServerRepository serverRepository;

    @Autowired
    public ServerCredentialsChecker(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    /**
     * Validates the provided server credentials by checking if they are not null and
     * verifying the server ID and password information with the data in the repository.
     *
     * @param serverCredentials an instance of {@link ServerCredentials} containing the server ID and password to validate
     * @return {@code true} if the server credentials are valid and match the data in the repository; {@code false} otherwise
     */

    public boolean check(ServerCredentials serverCredentials) {
        if (serverCredentials == null || serverCredentials.serverId() == null || serverCredentials.password() == null) {
            return false;
        }

        return serverRepository.findById(serverCredentials.serverId())
                .map(server -> server.getPassword().equals(serverCredentials.password()))
                .orElse(false);
    }
}
