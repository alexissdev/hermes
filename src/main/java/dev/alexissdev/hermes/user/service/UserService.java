package dev.alexissdev.hermes.user.service;

import dev.alexissdev.hermes.user.User;
import dev.alexissdev.hermes.user.page.PageResponse;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing User entities.
 * Provides methods to perform CRUD operations and additional queries on User data.
 */

public interface UserService {

    /**
     * Retrieves a list of all User entities.
     *
     * @return a List of User objects representing all users in the system
     */

    List<User> findAll();

    /**
     * Retrieves a paginated list of all User entities.
     *
     * @param page the page number to retrieve, with 0 being the first page
     * @param size the number of users to retrieve per page
     * @return a List of User objects representing the users in the requested page
     */

    PageResponse findAll(int page, int size);


    /**
     * Finds a User entity by its unique identifier.
     *
     * @param id the unique identifier of the user to retrieve
     * @return an Optional containing the User if found, or an empty Optional if no user exists with the given ID
     */

    Optional<User> findById(String id);

    /**
     * Finds a User entity based on its name.
     *
     * @param name the name of the User to be retrieved
     * @return an Optional containing the found User, or an empty Optional if no User exists with the given name
     */

    Optional<User> findByName(String name);

    /**
     * Saves the given User entity to the database. This method provides
     * a shorthand for saving the User without updating the cache. If the
     * user already exists, it will be updated in the database.
     *
     * @param user the User entity to be saved or updated
     * @return the saved or updated User entity
     */

    default Optional<User> save(User user) {
        return save(user, false);
    }

    /**
     * Saves the given User entity to the database. Optionally updates the cache
     * if the flag is set to true. If the user already exists, it will be updated.
     *
     * @param user the User entity to be saved or updated
     * @param updateCache a boolean flag indicating whether the cache should be updated
     * @return the saved or updated User entity
     */

    Optional<User> save(User user, boolean updateCache);

    /**
     * Updates the specified User entity in the cache.
     * If the user already exists in the cache, it will be replaced with the provided User object.
     *
     * @param user the User entity to update in the cache
     * @return the updated User entity as stored in the cache
     */

    User updateInCache(User user);

    /**
     * Deletes a User entity by its unique identifier.
     *
     * @param id the unique identifier of the user to be deleted
     * @return an Optional containing the deleted User if the operation was successful,
     *         or an empty Optional if no user with the given ID was found
     */

    Optional<User> deleteById(String id);
}
