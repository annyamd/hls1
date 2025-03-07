package ru.itmo.hls1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.hls1.model.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
