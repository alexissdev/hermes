package dev.alexissdev.hermes.user.repository;

import dev.alexissdev.hermes.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing User entities in the database.
 * Extends JpaRepository to provide CRUD operations and additional query methods.
 */

public interface UserRepository
        extends JpaRepository<User, String> {

    /**
     * Finds a User entity by its name.
     *
     * @param name the name of the user to be retrieved
     * @return an Optional containing the found User, or an empty Optional if no user is found
     */

    Optional<User> findByUsername(String name);

    /**
     * Checks if a User entity exists in the database with the specified name.
     *
     * @param name the name of the user to check for existence
     * @return true if a user with the specified name exists, false otherwise
     */

    boolean existsByUsername(String name);

    /**
     * Checks if a User entity exists in the database with the specified ID.
     *
     * @param id the ID of the user to check for existence
     * @return true if a user with the specified ID exists, false otherwise
     */

    boolean existsById(String id);

    /**
     * Retrieves a paginated list of all User entities from the database.
     *
     * @param pageable the pagination information, including page number, page size, and sorting options
     * @return a Page containing User entities for the requested page
     */

    Page<User> findAll(Pageable pageable);
}
