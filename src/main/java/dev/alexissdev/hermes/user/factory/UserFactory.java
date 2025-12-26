package dev.alexissdev.hermes.user.factory;

import dev.alexissdev.hermes.user.User;
import dev.alexissdev.hermes.user.controller.request.CreateUserRequest;
import dev.alexissdev.hermes.user.economy.UserEconomy;
import dev.alexissdev.hermes.user.statistic.UserStatistic;
import org.springframework.stereotype.Component;

/**
 * Factory class responsible for creating {@link User} instances.
 * <p>
 * This class encapsulates the logic for constructing a {@link User}
 * by integrating related components such as {@link UserEconomy}
 * and {@link UserStatistic}, ensuring all required dependencies
 * are properly initialized during the creation process.
 */

@Component
public class UserFactory {

    /**
     * Creates a new {@link User} instance based on the provided {@link CreateUserRequest}.
     * <p>
     * The method initializes a {@link User} with the following:
     * - A unique user ID derived from the request.
     * - A username derived from the request.
     * - A default {@link UserEconomy} instance with zero values for coins and gems.
     * - A default {@link UserStatistic} instance with zero values for all stats.
     *
     * @param request the {@link CreateUserRequest} containing the user's details
     * @return a new {@link User} instance constructed from the request data
     */


    public User from(CreateUserRequest request) {
        User user = new User();
        user.setId(request.id());
        user.setUsername(request.username());
        user.setLanguage(User.DEFAULT_LANGUAGE);

        UserEconomy economy = new UserEconomy();
        UserStatistic statistic = new UserStatistic();

        economy.setId(user.getId());
        economy.setUser(user);
        economy.setCoins(0.0);
        economy.setGems(0);
        statistic.setUser(user);
        statistic.setKills(0);
        statistic.setDeaths(0);
        statistic.setWins(0);
        statistic.setLosses(0);
        statistic.setId(user.getId());

        user.setEconomy(economy);
        user.setStatistic(statistic);

        return user;
    }
}
