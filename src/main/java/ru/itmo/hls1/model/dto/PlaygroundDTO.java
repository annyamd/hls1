package ru.itmo.hls1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class PlaygroundDTO {
    private int playgroundId;
    private String location;
    private String playgroundName;
    private List<Long> sportsId;
    private long playgroundAvailabilityId;
}
