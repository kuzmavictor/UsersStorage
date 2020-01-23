package ua.kharkiv.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kharkiv.storage.entity.Locality;

import java.util.UUID;

public interface LocalityRepository extends JpaRepository<Locality, UUID> {

    Locality findByName(String localityName);
}
