package ru.itmo.hls1.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.itmo.hls1.controllers.exceptions.not_found.NotFoundException;
import ru.itmo.hls1.model.dto.TeamDTO;
import ru.itmo.hls1.model.entity.Team;
import ru.itmo.hls1.sevice.util.GeneralService;
import ru.itmo.hls1.sevice.util.Mapper;

@Service
@RequiredArgsConstructor
public class TeamService extends GeneralService<Team, TeamDTO> {

    @Override
    protected NotFoundException getNotFoundIdException(long id) {
        return null;
    }

    @Override
    protected Mapper<Team, TeamDTO> getMapper() {
        return null;
    }

    @Override
    protected JpaRepository<Team, Long> getRepository() {
        return null;
    }
}
