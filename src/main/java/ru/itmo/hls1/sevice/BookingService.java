package ru.itmo.hls1.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.itmo.hls1.controllers.exceptions.BookingTimeIncorrectException;
import ru.itmo.hls1.controllers.exceptions.PlaygroundNotAvailableException;
import ru.itmo.hls1.controllers.exceptions.NoBookingTargetException;
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
            throw new PlaygroundNotAvailableException("", "");
        }

        checkTimeForBooking(
                playground.getId(),
                playground.getPlaygroundAvailability().getAvailableFrom(),
                playground.getPlaygroundAvailability().getAvailableTo(),
                dto.getStartTime(),
                dto.getEndTime()
        );

        return super.create(dto);
    }

    private void checkTimeForBooking(long pgId, LocalTime pgStartTime, LocalTime pgEndTime,
                                     LocalTime bookStartTime, LocalTime bookEndTime) {
        if (bookStartTime.isAfter(bookEndTime)) {
            throw new BookingTimeIncorrectException("start time must be earlier than end time");
        }
        if (bookStartTime.isBefore(pgStartTime)) {
            throw new BookingTimeIncorrectException("pg closed at this time");
        }
        if (bookEndTime.isAfter(pgEndTime)) {
            throw new BookingTimeIncorrectException("pg closed at this time");
        }

        int duration = getDurationMinutes(bookStartTime, bookEndTime);

        if (duration < BK_DURATION_MIN) {
            throw new BookingTimeIncorrectException("too small period of time");
        }
        if (duration > BK_DURATION_MAX) {
            throw new BookingTimeIncorrectException("too big period of time");
        }

        List<Booking> bookings = bookingRepository.findByPlayground_Id(pgId);
        bookings
                .forEach(it -> {
                    LocalTime start = it.getStartTime();
                    LocalTime end = it.getEndTime();
                    if (start.isAfter(bookStartTime) && start.isBefore(bookEndTime)
                            || end.isBefore(bookEndTime) && end.isAfter(start)) {
                        throw new BookingTimeIncorrectException("intersection with existing booking (" + start + "-" + end + ")");
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
                throw new NoBookingTargetException("", "");
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
