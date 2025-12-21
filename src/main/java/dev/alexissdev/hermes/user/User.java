package dev.alexissdev.hermes.user;

import dev.alexissdev.hermes.user.economy.UserEconomy;
import dev.alexissdev.hermes.user.statistic.UserStatistic;
import jakarta.persistence.*;
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

    @Id
    private String id;
    private String name;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "economy_id")
    private UserEconomy economy;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "statistic_id")
    private UserStatistic statistic;
}
