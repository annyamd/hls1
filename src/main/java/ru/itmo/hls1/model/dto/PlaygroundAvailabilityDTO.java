package ru.itmo.hls1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaygroundAvailabilityDTO {
    private Long id;
    private Boolean isAvailable;
    private LocalTime availableFrom;
    private LocalTime availableTo;
    private Integer capacity;
}
