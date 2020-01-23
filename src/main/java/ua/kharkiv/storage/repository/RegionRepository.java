package ua.kharkiv.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kharkiv.storage.entity.Region;

import java.util.UUID;

public interface RegionRepository extends JpaRepository<Region, UUID> {

    Region findByName(String regionName);
}
