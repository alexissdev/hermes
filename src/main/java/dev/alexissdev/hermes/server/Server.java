package dev.alexissdev.hermes.server;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a server entity with an identifier and a password.
 * <p>
 * This class is annotated with JPA and Lombok annotations to enable
 * object-relational mapping and simplify boilerplate code like getters,
 * setters, constructors, etc.
 * <p>
 * An instance of this class corresponds to a record in the database table
 * representing servers.
 * <p>
 * Annotations:
 * - @Entity: Marks this class as a JPA entity.
 * - @Id: Indicates that the id field is the primary key.
 * - @NoArgsConstructor: Generates a no-argument constructor.
 * - @AllArgsConstructor: Generates a constructor with all fields.
 * - @Getter: Generates getter methods for all fields.
 * - @Setter: Generates setter methods for all fields.
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Server {

    @Id
    private String id;
    private String password;
}
