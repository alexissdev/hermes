package dev.alexissdev.hermes.user.controller.request;

/**
 * Record CreateUserRequest represents a request for creating a user in the system.
 * <p>
 * The record encapsulates the following user details:
 * - A unique identifier (id) for the user.
 * - The username of the user.
 */

public record CreateUserRequest(String id, String username) {
}
