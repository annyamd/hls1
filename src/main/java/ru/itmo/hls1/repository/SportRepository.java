package ru.itmo.hls1.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.hls1.model.entity.Sport;

@Repository
public interface SportRepository extends CrudRepository<Sport, Long> {
}
