package ru.itmo.hls1.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.hls1.controllers.exceptions.already_applied.PlayerAlreadyInTeamException;
import ru.itmo.hls1.controllers.exceptions.unavailable_action.TeamManagerNotHisTeamException;
import ru.itmo.hls1.controllers.exceptions.unavailable_action.TeamNoSpaceException;
import ru.itmo.hls1.controllers.exceptions.not_found.NotFoundException;
import ru.itmo.hls1.controllers.exceptions.not_found.PlayerNotFoundException;
import ru.itmo.hls1.controllers.exceptions.not_found.TeamManagerNotFoundException;
import ru.itmo.hls1.controllers.exceptions.not_found.TeamNotFoundException;
import ru.itmo.hls1.model.dto.TeamDTO;
import ru.itmo.hls1.model.entity.Player;
import ru.itmo.hls1.model.entity.Team;
import ru.itmo.hls1.model.entity.TeamManager;
import ru.itmo.hls1.repository.PlayerRepository;
import ru.itmo.hls1.repository.TeamManagerRepository;
import ru.itmo.hls1.repository.TeamRepository;
import ru.itmo.hls1.sevice.util.GeneralService;
import ru.itmo.hls1.sevice.util.Mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService extends GeneralService<Team, TeamDTO> {
    private final static long DEFAULT_TEAM_SIZE = 10;

    private final Mapper<Team, TeamDTO> mapper = new TeamMapper();
    private final TeamRepository teamRepository;
    private final TeamManagerRepository teamManagerRepository;
    private final PlayerRepository playerRepository;

    //    by team manager (team controller)
//    by player himself (player controller)
    @Transactional
    public void addMember(long teamId, long playerId, long teamManagerId) {
        Team team = getEntityById(teamId);
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("id = " + playerId));
        if (team.getManager().getTeamManagerId() != teamManagerId) {
            throw new TeamManagerNotHisTeamException(teamId, teamManagerId);
        }
        if (team.getTeamSize() == team.getPlayers().size()) {
            throw new TeamNoSpaceException(teamId);
        }
        if (team.getPlayers().contains(player)) {
            throw new PlayerAlreadyInTeamException(playerId, teamId);
        }
        team.getPlayers().add(player);
        teamRepository.save(team);
    }

    @Transactional
    public void removeMember(long teamId, long playerId, long teamManager) {
        Team team = getEntityById(teamId);
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("id = " + playerId));
        if (team.getManager().getTeamManagerId() != teamManager) {
            throw new TeamManagerNotHisTeamException(teamId, teamManager);
        }
        if (!team.getPlayers().contains(player)) {
            throw new PlayerNotFoundException("id = " + playerId + ", teamId = " + teamId);
        }
        team.getPlayers().remove(player);
        teamRepository.save(team);
    }

    public List<TeamDTO> getAllTeamsByManager(long managerId) {
        TeamManager manager = teamManagerRepository.findById(managerId)
                .orElseThrow(() -> new TeamManagerNotFoundException("id = " + managerId));
        return manager.getTeams()
                .stream()
                .map(mapper::entityToDto)
                .toList();
    }

    public TeamDTO getTeamByManager(long managerId, long teamId) {
        teamManagerRepository.findById(managerId)
                .orElseThrow(() -> new TeamManagerNotFoundException("id = " + managerId));
        Team team = getEntityById(teamId);
        if (team.getManager().getTeamManagerId() != managerId) {
            throw new TeamManagerNotHisTeamException(managerId, teamId);
        }
        return mapper.entityToDto(team);
    }

    @Transactional
    public TeamDTO update(long teamId, TeamDTO dto) {
        Team found = getEntityById(teamId);
        Team updated = mapper.dtoToEntity(dto);
        updated.setTeamId(teamId);
        updated.setManager(found.getManager());
        updated.setBookingList(found.getBookingList());
        teamRepository.save(updated);
        return mapper.entityToDto(updated);
    }

    public void delete(long managerId, long id) {
        teamManagerRepository.findById(managerId)
                .orElseThrow(() -> new TeamManagerNotFoundException("id = " + managerId));
        super.delete(id);
    }

    public List<TeamDTO> getTeamsByPlayer(long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("id = " + playerId));
        return player.getTeams()
                .stream()
                .map(mapper::entityToDto)
                .toList();
    }

    @Override
    protected NotFoundException getNotFoundIdException(long id) {
        return new TeamNotFoundException("id = " + id);
    }

    @Override
    protected Mapper<Team, TeamDTO> getMapper() {
        return mapper;
    }

    @Override
    protected JpaRepository<Team, Long> getRepository() {
        return teamRepository;
    }

    class TeamMapper implements Mapper<Team, TeamDTO> {

        @Override
        public TeamDTO entityToDto(Team entity) {
            return new TeamDTO(
                    entity.getTeamId(),
                    entity.getTeamName(),
                    entity.getManager().getTeamManagerId(),
                    entity.getTeamSize(),
                    entity.getIsFreeToJoin(),
                    entity.getPlayers()
                            .stream()
                            .map(Player::getPlayerId)
                            .collect(Collectors.toSet())
            );
        }

        @Override
        public Team dtoToEntity(TeamDTO dto) {
            long size = DEFAULT_TEAM_SIZE;
            if (dto.getTeamSize() != null) {
                size = dto.getTeamSize();
            }

            Set<Player> players;
            if (dto.getPlayersId() != null) {
                if (size < dto.getPlayersId().size()) {
                    throw new TeamNoSpaceException(-1);
                }
                players = new HashSet<>(playerRepository.findAllById(dto.getPlayersId()));
                if (players.size() != dto.getPlayersId().size()) {
                    throw new PlayerNotFoundException("one or more of players list elements");
                }
            } else {
                players = new HashSet<>();
            }

            TeamManager teamManager = teamManagerRepository.findById(dto.getTeamManagerId())
                    .orElseThrow(() -> new TeamManagerNotFoundException("id = " + dto.getTeamManagerId()));

            return new Team(
                    null,
                    dto.getTeamName(),
                    size,
                    dto.getIsFreeToJoin(),
                    players,
                    teamManager,
                    null
            );
        }
    }
}
