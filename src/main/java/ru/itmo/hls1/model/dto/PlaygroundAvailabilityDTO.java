package ru.itmo.hls1.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PlaygroundAvailabilityDTO {
    private int id;
    private int playgroundId;
    private int sportTypeId;
    private Date available_from;
    private Date available_to;
}
