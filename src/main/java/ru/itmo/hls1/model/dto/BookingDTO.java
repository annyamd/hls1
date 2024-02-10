package ru.itmo.hls1.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class BookingDTO {
    private Long id;
    private Long playgroundId;
    private Long playerId;
    private Long teamId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    @JsonFormat(pattern = "hh:mm")
    private LocalTime startTime;
    @JsonFormat(pattern = "hh:mm")
    private LocalTime endTime;
}
