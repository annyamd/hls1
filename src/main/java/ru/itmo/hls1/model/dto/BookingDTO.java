package ru.itmo.hls1.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

import static ru.itmo.hls1.controllers.util.ValidationMessages.MSG_ID_NEGATIVE;

@Data
@AllArgsConstructor
public class BookingDTO {

    @Min(value = 0, message = MSG_ID_NEGATIVE)
    private Long id;

    @Min(value = 0, message = MSG_ID_NEGATIVE)
    @NotNull(message = "playgroundId field can't be null")
    private Long playgroundId;

    @Min(value = 0, message = MSG_ID_NEGATIVE)
    @NotNull(message = "playerId field can't be null")
    private Long playerId;

    @Min(value = 0, message = MSG_ID_NEGATIVE)
    @NotNull(message = "teamId field can't be null")
    private Long teamId;

    @Future(message = "date must be at least tomorrow")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "date field can't be null")
    private LocalDate date;

    @JsonFormat(pattern = "hh:mm")
    @NotNull(message = "startTime field can't be null")
    private LocalTime startTime;

    @JsonFormat(pattern = "hh:mm")
    @NotNull(message = "endTime field can't be null")
    private LocalTime endTime;
}
