package ru.itmo.hls1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PlaygroundAvailabilityDTO {
    private Long id;

    private Boolean availability;

    private LocalDateTime available_from;

    private LocalDateTime available_to;

    private PlaygroundDTO playground;
}
