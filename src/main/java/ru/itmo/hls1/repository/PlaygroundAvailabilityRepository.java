package ru.itmo.hls1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.hls1.model.entity.PlaygroundAvailability;

import java.util.List;

@Repository
public interface PlaygroundAvailabilityRepository extends JpaRepository<PlaygroundAvailability, Long> {
}
