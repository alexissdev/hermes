package dev.alexissdev.hermes.user.statistic;

import dev.alexissdev.hermes.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserStatistic {

    @Id
    private String id;

    private Integer kills;
    private Integer deaths;
    private Integer wins;
    private Integer losses;

    @OneToOne(mappedBy = "statistic")
    private User user;
}
