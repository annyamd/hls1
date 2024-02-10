package ru.itmo.hls1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.hls1.model.entity.TeamManager;

@Repository
public interface TeamManagerRepository extends JpaRepository<TeamManager, Long> {
}
