package ru.itmo.hls1.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.itmo.hls1.controllers.exceptions.not_found.NotFoundException;
import ru.itmo.hls1.controllers.exceptions.not_found.TeamManagerNotFoundException;
import ru.itmo.hls1.controllers.exceptions.not_found.UserNotFoundException;
import ru.itmo.hls1.controllers.exceptions.already_applied.RoleAlreadyGrantedException;
import ru.itmo.hls1.model.dto.TeamManagerDTO;
import ru.itmo.hls1.model.entity.Role;
import ru.itmo.hls1.model.entity.TeamManager;
import ru.itmo.hls1.model.entity.User;
import ru.itmo.hls1.repository.TeamManagerRepository;
import ru.itmo.hls1.repository.UserRepository;
import ru.itmo.hls1.sevice.util.GeneralService;
import ru.itmo.hls1.sevice.util.Mapper;

@Service
@RequiredArgsConstructor
public class TeamManagerService extends GeneralService<TeamManager, TeamManagerDTO> {

    private final TeamManagerRepository repository;
    private final UserRepository userRepository;
    private final Mapper<TeamManager, TeamManagerDTO> mapper = new TeamManagerMapper();

    @Override
    public TeamManagerDTO create(TeamManagerDTO dto) {
        long id = dto.getUserId();
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id = " + id));
        if (user.getRoles().contains(Role.TEAM_MANAGER)) {
            throw new RoleAlreadyGrantedException(id, Role.TEAM_MANAGER);
        }
        TeamManagerDTO createdDto = super.create(dto);
        user.getRoles().add(Role.TEAM_MANAGER);
        userRepository.save(user);
        return createdDto;
    }

    @Override
    public void delete(long id) {
        User user = getEntityById(id).getUser();
        super.delete(id);
        user.getRoles().remove(Role.TEAM_MANAGER);
        userRepository.save(user);
    }

    public TeamManagerDTO update(long id, TeamManagerDTO dto) {
        TeamManager found = getEntityById(id);
        TeamManager updated = mapper.dtoToEntity(dto);
        updated.setTeamManagerId(id);
        updated.setUser(found.getUser());
        updated.setTeams(found.getTeams());
        repository.save(updated);
        return mapper.entityToDto(updated);
    }

    @Override
    protected NotFoundException getNotFoundIdException(long id) {
        return new TeamManagerNotFoundException("id = " + id);
    }

    @Override
    protected Mapper<TeamManager, TeamManagerDTO> getMapper() {
        return mapper;
    }

    @Override
    protected JpaRepository<TeamManager, Long> getRepository() {
        return repository;
    }

    class TeamManagerMapper implements Mapper<TeamManager, TeamManagerDTO> {

        @Override
        public TeamManagerDTO entityToDto(TeamManager entity) {
            return new TeamManagerDTO(
                    entity.getTeamManagerId(),
                    entity.getUser().getUserId(),
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getPhone(),
                    entity.getEmail()
            );
        }

        @Override
        public TeamManager dtoToEntity(TeamManagerDTO dto) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("id = " + dto.getUserId()));
            return new TeamManager(
                    null,
                    dto.getFirstName(),
                    dto.getLastName(),
                    dto.getPhone(),
                    dto.getEmail(),
                    user,
                    null
            );
        }
    }
}
