package dev.alexissdev.hermes.user.controller.request;

/**
 * Record UpdateUserRequest represents a request to update an existing user's game statistics.
 * <p>
 * The record encapsulates the following user stats:
 * - coins: The amount of in-game currency the user has.
 * - gems: The quantity of gems owned by the user.
 * - kills: The total number of kills achieved by the user.
 * - deaths: The total number of times the user has been defeated.
 * - wins: The total count of games won by the user.
 * - losses: The total count of games lost by the user.
 */

public record UpdateUserRequest(
        double coins,
        int gems,
        int kills,
        int deaths,
        int wins,
        int losses
) {}
