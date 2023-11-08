package ru.itmo.hls1.sevice;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.hls1.model.dto.BookingRecordDTO;
import ru.itmo.hls1.model.dto.PlaygroundDTO;
import ru.itmo.hls1.model.dto.PlaygroundDetailsDTO;
import ru.itmo.hls1.model.dto.TeamDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaygroundService {

    public List<PlaygroundDTO> findAllPlaygrounds(){
        List<PlaygroundDTO> list = new ArrayList<>();
        list.add(new PlaygroundDTO(0, "Name 0", "Location 0"));
        list.add(new PlaygroundDTO(1, "Name 1", "Location 1"));
        list.add(new PlaygroundDTO(2, "Name 2", "Location 2"));
        return list;
    }

    public PlaygroundDetailsDTO getPlaygroundDetails(int id) {
        //        get from repository and return info in PlaygroundDetailsDTO object
        return new PlaygroundDetailsDTO(new PlaygroundDTO(1, "Moscow, Street", "Sport center"));
    }

    public List<BookingRecordDTO> getBookingRecordsByUser(String login) {
//        get from repository
        List<BookingRecordDTO> bookingRecords = new ArrayList<>();
        bookingRecords.add(new BookingRecordDTO(login, 1,
                LocalDateTime.of(2023, 11, 21, 11, 0),
                LocalDateTime.of(2023, 11, 21, 12, 0)));

        bookingRecords.add(new BookingRecordDTO(login, 1,
                LocalDateTime.of(2023, 11, 21, 16, 0),
                LocalDateTime.of(2023, 11, 21, 17, 0)));

        bookingRecords.add(new BookingRecordDTO(login, 1,
                LocalDateTime.of(2023, 11, 23, 14, 0),
                LocalDateTime.of(2023, 11, 23, 15, 0)));
//
        return bookingRecords;
    }

    public List<Long> getUserTeamsIds(String login) {
        List<Long> teams = new ArrayList<>();
        teams.add(1L);
        teams.add(35345L);
        teams.add(234211L);
        return teams;
    }

}
