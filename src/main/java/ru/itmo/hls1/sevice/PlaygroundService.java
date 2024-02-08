package ru.itmo.hls1.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.hls1.model.dto.*;
import ru.itmo.hls1.model.entity.Playground;
import ru.itmo.hls1.model.entity.PlaygroundAvailability;
import ru.itmo.hls1.repository.PlaygroundAvailabilityRepository;
import ru.itmo.hls1.repository.PlaygroundRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaygroundService {

    private final PlaygroundRepository playgroundRepository;
    private final PlaygroundAvailabilityRepository pgAvailabilityRepository;

    public List<Playground> findAllPlaygrounds(){
        List<Playground> target = new ArrayList<>();
        playgroundRepository.findAll().forEach(target::add);
        return target;
    }

    public Playground getPlayground(Long id) {
        return playgroundRepository.findById(id).orElse(null);
    }

    public PlaygroundAvailability getPlaygroundDetails(Long id) {
        return pgAvailabilityRepository.findPlaygroundAvailabilityByPlayground_id(id);
    }

//    public List<BookingDTO> getBookingRecordsByUser(String login) {
////        get from repository
//        List<BookingDTO> bookingRecords = new ArrayList<>();
//        bookingRecords.add(new BookingDTO(login, 1,
//                LocalDateTime.of(2023, 11, 21, 11, 0),
//                LocalDateTime.of(2023, 11, 21, 12, 0)));
//
//        bookingRecords.add(new BookingDTO(login, 1,
//                LocalDateTime.of(2023, 11, 21, 16, 0),
//                LocalDateTime.of(2023, 11, 21, 17, 0)));
//
//        bookingRecords.add(new BookingDTO(login, 1,
//                LocalDateTime.of(2023, 11, 23, 14, 0),
//                LocalDateTime.of(2023, 11, 23, 15, 0)));
////
//        return bookingRecords;
//    }

    public List<Long> getUserTeamsIds(long id) {
        List<Long> teams = new ArrayList<>();
        teams.add(1L);
        teams.add(35345L);
        teams.add(234211L);
        return teams;
    }

}
