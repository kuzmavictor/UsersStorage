package ua.kharkiv.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kharkiv.storage.entity.User;

import java.util.UUID;

/**
 * A repository to work with data about users.
 */
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByEmail(String userEmail);
}
