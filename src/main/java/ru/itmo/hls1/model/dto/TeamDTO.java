package ru.itmo.hls1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamDTO {
    private long teamId;
    private String teamName;
    private long teamManagerId;
    private int teamSize;
    private boolean isFreeToJoin;
}
