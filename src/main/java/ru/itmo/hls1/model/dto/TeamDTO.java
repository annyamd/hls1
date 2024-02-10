package ru.itmo.hls1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamDTO {
    private Long teamId;
    private String teamName;
    private Long teamManagerId;
    private Integer teamSize;
    private Boolean isFreeToJoin;
}
