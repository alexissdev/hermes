package dev.alexissdev.hermes.auth.credentials;

/**
 * Represents the credentials required to access a specific server.
 * This record encapsulates the server identifier and the associated password identifier.
 * <p>
 * This class is immutable and thread-safe as it is implemented as a record.
 *
 * @param serverId   the unique identifier of the server
 * @param password the unique identifier of the password associated with the server
 */
public record ServerCredentials(String serverId, String password) {
}
