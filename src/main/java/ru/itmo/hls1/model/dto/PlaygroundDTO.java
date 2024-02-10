package ru.itmo.hls1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaygroundDTO {
    private long  playgroundId;
    private String location;
    private String playgroundName;
    private Float latitude;
    private Float longitude;
    private List<Long> sportsId;
    private PlaygroundAvailabilityDTO playgroundAvailability;
}
