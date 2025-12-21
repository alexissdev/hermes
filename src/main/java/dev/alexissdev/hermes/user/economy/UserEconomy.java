package dev.alexissdev.hermes.user.economy;

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
public class UserEconomy {

    @Id
    private String id;
    private Double coins;
    private Integer gems;

    @OneToOne(mappedBy = "economy")
    private User user;
}
