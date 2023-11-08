package ru.itmo.hls1.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlaygroundAvailabilityDTO {
    private int id;
    private int playgroundId;
    private int sportTypeId;
    private LocalDateTime available_from;
    private LocalDateTime available_to;
}
