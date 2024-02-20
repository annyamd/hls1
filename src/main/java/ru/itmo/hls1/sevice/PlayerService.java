package ru.itmo.hls1.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.itmo.hls1.controllers.exceptions.unavailable_action.TeamClosedException;
import ru.itmo.hls1.controllers.exceptions.not_found.NotFoundException;
import ru.itmo.hls1.controllers.exceptions.not_found.PlaygroundNotFoundException;
import ru.itmo.hls1.controllers.exceptions.not_found.TeamNotFoundException;
import ru.itmo.hls1.controllers.exceptions.already_applied.RoleAlreadyGrantedException;
import ru.itmo.hls1.controllers.exceptions.not_found.UserNotFoundException;
import ru.itmo.hls1.model.dto.PlayerDTO;
import ru.itmo.hls1.model.dto.TeamDTO;
import ru.itmo.hls1.model.entity.Player;
import ru.itmo.hls1.model.entity.Role;
import ru.itmo.hls1.model.entity.Team;
import ru.itmo.hls1.model.entity.User;
import ru.itmo.hls1.repository.PlayerRepository;
import ru.itmo.hls1.repository.TeamRepository;
import ru.itmo.hls1.repository.UserRepository;
import ru.itmo.hls1.sevice.util.GeneralService;
import ru.itmo.hls1.sevice.util.Mapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService extends GeneralService<Player, PlayerDTO> {

    private final Mapper<Player, PlayerDTO> mapper = new PlayerMapper();
    private final PlayerRepository playerRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final TeamService teamService;

    @Override
    public PlayerDTO create(PlayerDTO dto) {
        long id = dto.getUserId();
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id = " + id));
        if (user.getRoles().contains(Role.PLAYER)) {
            throw new RoleAlreadyGrantedException(id, Role.PLAYER);
        }
        PlayerDTO createdDto = super.create(dto);
        user.getRoles().add(Role.PLAYER);
        userRepository.save(user);
        return createdDto;
    }

    @Override
    public void delete(long id) {
        User user = getEntityById(id).getUser();
        super.delete(id);
        user.getRoles().remove(Role.PLAYER);
        userRepository.save(user);
    }

    public PlayerDTO update(long id, PlayerDTO dto) {
        Player found = getEntityById(id);
        Player updated = mapper.dtoToEntity(dto);
        updated.setPlayerId(found.getPlayerId());
        updated.setUser(found.getUser());
        updated.setTeams(found.getTeams());
        updated.setBookingList(updated.getBookingList());
        playerRepository.save(updated);
        return mapper.entityToDto(updated);
    }

    public void joinTeam(long playerId, long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException("id = " + teamId));
        if (!team.getIsFreeToJoin()) {
            throw new TeamClosedException(teamId);
        }
        teamService.addMember(teamId, playerId, team.getManager().getTeamManagerId());
    }

    public void leaveTeam(long playerId, long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException("id = " + teamId));
        teamService.removeMember(teamId, playerId, team.getManager().getTeamManagerId());
    }

    @Override
    protected NotFoundException getNotFoundIdException(long id) {
        return new PlaygroundNotFoundException("id = " + id);
    }

    @Override
    protected Mapper<Player, PlayerDTO> getMapper() {
        return mapper;
    }

    @Override
    protected JpaRepository<Player, Long> getRepository() {
        return playerRepository;
    }

    class PlayerMapper implements Mapper<Player, PlayerDTO> {

        @Override
        public PlayerDTO entityToDto(Player entity) {
            return new PlayerDTO(
                    entity.getPlayerId(),
                    entity.getUser().getUserId(),
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getAge(),
                    entity.getHeight(),
                    entity.getWeight(),
                    entity.getGender()
            );
        }

        @Override
        public Player dtoToEntity(PlayerDTO dto) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("id = " + dto.getUserId()));

            return new Player(
                    null,
                    dto.getFirstName(),
                    dto.getLastName(),
                    dto.getAge(),
                    dto.getHeightCm(),
                    dto.getWeightKg(),
                    dto.getGender(),
                    user,
                    null,
                    null

            );
        }
    }

}
