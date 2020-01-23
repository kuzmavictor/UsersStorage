package ua.kharkiv.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kharkiv.storage.entity.Role;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findByName(String roleName);
}
