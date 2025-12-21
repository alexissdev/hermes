package dev.alexissdev.hermes.server.repository;

import dev.alexissdev.hermes.server.Server;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Server entities.
 *
 * This interface extends JpaRepository, providing built-in methods for
 * CRUD operations and database access. The Server entity is identified by
 * a String type ID.
 */
public interface ServerRepository
        extends JpaRepository<Server, String> {
}
