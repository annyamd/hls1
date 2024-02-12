package ru.itmo.hls1.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.itmo.hls1.controllers.exceptions.not_found.NotFoundException;
import ru.itmo.hls1.model.dto.TeamManagerDTO;
import ru.itmo.hls1.model.entity.TeamManager;
import ru.itmo.hls1.sevice.util.GeneralService;
import ru.itmo.hls1.sevice.util.Mapper;

@Service
@RequiredArgsConstructor
public class TeamManagerService extends GeneralService<TeamManager, TeamManagerDTO> {


    @Override
    protected NotFoundException getNotFoundIdException(long id) {
        return null;
    }

    @Override
    protected Mapper<TeamManager, TeamManagerDTO> getMapper() {
        return null;
    }

    @Override
    protected JpaRepository<TeamManager, Long> getRepository() {
        return null;
    }
}
