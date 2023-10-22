package ru.itmo.hls1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaygroundDTO {
    private int playgroundId;
    private String location;
    private String playgroundName;

}
