package dev.alexissdev.hermes.user;

import dev.alexissdev.hermes.user.controller.response.UserResponse;
import dev.alexissdev.hermes.user.economy.UserEconomy;
import dev.alexissdev.hermes.user.statistic.UserStatistic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public static final User NULL_USER = null;
    public static final String DEFAULT_LANGUAGE = "en";

    @Id
    private String id;
    private String username;
    private String language;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "economy_id")
    private UserEconomy economy;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "statistic_id")
    private UserStatistic statistic;

    /**
     * Transforms a {@code UserResponse} object into a {@code User} entity.
     *
     * @param response the {@code UserResponse} object containing user details to be transformed
     * @return a new {@code User} entity initialized with the data from the given {@code UserResponse}
     */

    public static User from(UserResponse response) {
        return new User(
                response.id(),
                response.username(),
                response.language(),
                new UserEconomy(response.id(), response.coins(), response.gems(), null),
                new UserStatistic(
                        response.id(),
                        response.kills(),
                        response.deaths(),
                        response.wins(),
                        response.losses(),
                        null
                ));
    }
}
