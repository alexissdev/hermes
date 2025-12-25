package dev.alexissdev.hermes.user.controller.response;

import dev.alexissdev.hermes.user.User;

/**
 * Record UserResponse represents the response sent back to the client after user-related operations.
 * <p>
 * This response encapsulates the following details about a user:
 * - id: Unique identifier of the user.
 * - username: The name of the user.
 * - language: The preferred language of the user.
 * - coins: The amount of in-game currency (coins) associated with the user.
 * - gems: The number of special in-game currency (gems) the user possesses.
 * - kills: The number of kills achieved by the user.
 * - deaths: The number of times the user has died.
 * - wins: The total number of games won by the user.
 * - losses: The total number of games the user has lost.
 */

public record UserResponse(String id,
                           String username,
                           String language,
                           double coins,
                           int gems, int
                           kills,
                           int deaths,
                           int wins,
                           int losses
) {

    /**
     * Creates a {@code UserResponse} object from a {@code User} entity.
     *
     * @param user the {@code User} entity containing the details to be transformed into a {@code UserResponse}
     * @return a {@code UserResponse} object with details derived from the provided {@code User} entity
     */

    public static UserResponse from(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getLanguage(),
                user.getEconomy().getCoins(),
                user.getEconomy().getGems(),
                user.getStatistic().getKills(),
                user.getStatistic().getDeaths(),
                user.getStatistic().getWins(),
                user.getStatistic().getLosses()
        );
    }
}
