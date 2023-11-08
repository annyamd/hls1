package ru.itmo.hls1.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BookingRecordDTO {
    private String login;
    private long playgroundId;

    @JsonFormat(pattern="dd-MM-yyyy hh:mm:ss")
    private LocalDateTime start_time;

    @JsonFormat(pattern="dd-MM-yyyy hh:mm:ss")
    private LocalDateTime end_time;
}
