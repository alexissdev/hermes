package dev.alexissdev.hermes.user.controller.request;

/**
 * Record UpdateUserRequest represents a request to update user details in the system.
 * <p>
 * The record encapsulates key fields that are used to modify a user's profile.
 * <p>
 * Fields:
 * - language: The language preference of the user.
 * - coins: The amount of in-game currency (coins) the user has.
 * - gems: The amount of special in-game currency (gems) the user possesses.
 * - kills: The number of kills the user has achieved.
 * - deaths: The number of times the user has died.
 * - wins: The number of games the user has won.
 * - losses: The number of games the user has lost.
 */

public record UpdateUserRequest(
        String language,
        double coins,
        int gems,
        int kills,
        int deaths,
        int wins,
        int losses
) {}
