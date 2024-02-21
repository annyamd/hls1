package ru.itmo.hls1.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.itmo.hls1.controllers.exceptions.invalid.InvalidBookingTimeException;
import ru.itmo.hls1.controllers.exceptions.unavailable_action.PlaygroundNotAvailableException;
import ru.itmo.hls1.controllers.exceptions.invalid.NoBookingTargetException;
import ru.itmo.hls1.controllers.exceptions.not_found.*;
import ru.itmo.hls1.model.dto.BookingDTO;
import ru.itmo.hls1.model.entity.Booking;
import ru.itmo.hls1.model.entity.Player;
import ru.itmo.hls1.model.entity.Playground;
import ru.itmo.hls1.model.entity.Team;
import ru.itmo.hls1.repository.BookingRepository;
import ru.itmo.hls1.repository.PlayerRepository;
import ru.itmo.hls1.repository.PlaygroundRepository;
import ru.itmo.hls1.repository.TeamRepository;
import ru.itmo.hls1.sevice.util.GeneralService;
import ru.itmo.hls1.sevice.util.Mapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService extends GeneralService<Booking, BookingDTO> {
    private final static int BK_DURATION_MAX = 100;
    private final static int BK_DURATION_MIN = 15;

    private final Mapper<Booking, BookingDTO> mapper = new BookingMapper();
    private final BookingRepository bookingRepository;
    private final PlaygroundRepository playgroundRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    @Override
    public BookingDTO create(BookingDTO dto) {
        Playground playground = playgroundRepository.findById(dto.getPlaygroundId())
                .orElseThrow(() -> new PlayerNotFoundException("id = " + dto.getPlaygroundId()));
        if (!playground.getPlaygroundAvailability().getIsAvailable()) {
            throw new PlaygroundNotAvailableException(playground.getId());
        }

        int size = 1;
        if (dto.getPlayerId() == null && dto.getTeamId() != null) {
            Team team = teamRepository.findById(dto.getTeamId())
                    .orElseThrow(() -> new TeamNotFoundException("id = " + dto.getTeamId()));
            size = team.getTeamSize().intValue();
        }

        checkTimeForBooking(
                playground,
                dto.getDate(),
                playground.getPlaygroundAvailability().getAvailableFrom(),
                playground.getPlaygroundAvailability().getAvailableTo(),
                dto.getStartTime(),
                dto.getEndTime(),
                size
        );

        return super.create(dto);
    }

    public List<BookingDTO> getBookingsByPlayer(long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("id = " + playerId));
        return player.getBookingList()
                .stream()
                .map(mapper::entityToDto)
                .toList();
    }

    public List<BookingDTO> getBookingsByTeam(long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException("id = " + teamId));
        return team.getBookingList()
                .stream()
                .map(mapper::entityToDto)
                .toList();
    }

    private void checkTimeForBooking(Playground playground, LocalDate date, LocalTime pgStartTime, LocalTime pgEndTime,
                                     LocalTime bookStartTime, LocalTime bookEndTime, int size) {
        if (bookStartTime.isAfter(bookEndTime)) {
            throw new InvalidBookingTimeException("start time must be earlier than end time");
        }
        if (bookStartTime.isBefore(pgStartTime)) {
            throw new InvalidBookingTimeException("pg closed at this time");
        }
        if (bookEndTime.isAfter(pgEndTime)) {
            throw new InvalidBookingTimeException("pg closed at this time");
        }

        int duration = getDurationMinutes(bookStartTime, bookEndTime);

        if (duration < BK_DURATION_MIN) {
            throw new InvalidBookingTimeException("too small period of time");
        }
        if (duration > BK_DURATION_MAX) {
            throw new InvalidBookingTimeException("too big period of time");
        }

        List<Booking> bookings = bookingRepository.findByPlayground_Id(playground.getId());

        int capacity = playground.getPlaygroundAvailability().getCapacity();
        final int[] curCount = new int[1];
        curCount[0] = size;

        bookings
                .forEach(it -> {
                    if (date.isEqual(it.getDate())) {
                        LocalTime start = it.getStartTime();
                        LocalTime end = it.getEndTime();
                        if (start.isAfter(bookStartTime) && start.isBefore(bookEndTime)
                                || end.isBefore(bookEndTime) && end.isAfter(start)) {
                            if (it.getPlayer() != null) {
                                curCount[0] += 1;
                            } else if (it.getTeam() != null) {
                                curCount[0] += it.getTeam().getTeamSize();
                            }
                            if (curCount[0] > capacity) {
                                throw new InvalidBookingTimeException("no space in chosen playground");
                            }
                        }
                    }
                });
    }

    private int getDurationMinutes(LocalTime bookStartTime, LocalTime bookEndTime) {
        return 60 * (bookEndTime.getHour() - bookStartTime.getHour())
                + (bookEndTime.getMinute() - bookStartTime.getMinute());
    }

    @Override
    protected NotFoundException getNotFoundIdException(long id) {
        return new BookingNotFoundException("id = " + id);
    }

    @Override
    protected Mapper<Booking, BookingDTO> getMapper() {
        return mapper;
    }

    @Override
    protected JpaRepository<Booking, Long> getRepository() {
        return bookingRepository;
    }

    class BookingMapper implements Mapper<Booking, BookingDTO> {

        @Override
        public BookingDTO entityToDto(Booking entity) {
            Long teamId = null;
            Long playerId = null;
            if (entity.getTeam() != null) {
                teamId = entity.getTeam().getTeamId();
            } else {
                playerId = entity.getPlayer().getPlayerId();
            }
            return new BookingDTO(
                    entity.getBookingId(),
                    entity.getPlayground().getId(),
                    playerId,
                    teamId,
                    entity.getDate(),
                    entity.getStartTime(),
                    entity.getEndTime()
            );
        }

        @Override
        public Booking dtoToEntity(BookingDTO dto) {
            Playground playground = playgroundRepository.findById(dto.getPlaygroundId())
                    .orElseThrow(() -> new PlaygroundNotFoundException("id = " + dto.getPlaygroundId()));
            Player player = null;
            Team team = null;
            if (dto.getPlayerId() != null) {
                player = playerRepository.findById(dto.getPlayerId())
                        .orElseThrow(() -> new PlayerNotFoundException("id = " + dto.getPlayerId()));
            } else if (dto.getTeamId() != null) {
                team = teamRepository.findById(dto.getTeamId())
                        .orElseThrow(() -> new TeamNotFoundException("id = " + dto.getTeamId()));
            } else {
                throw new NoBookingTargetException();
            }
            return new Booking(
                    null,
                    dto.getDate(),
                    dto.getStartTime(),
                    dto.getEndTime(),
                    playground,
                    player,
                    team
            );
        }
    }

}
