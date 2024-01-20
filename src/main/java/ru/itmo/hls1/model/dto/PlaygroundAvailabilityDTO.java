package ru.itmo.hls1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class PlaygroundAvailabilityDTO {
    private long id;
    private Boolean availability;
    private LocalTime availableFrom;
    private LocalTime availableTo;
    private int capacity;
}
