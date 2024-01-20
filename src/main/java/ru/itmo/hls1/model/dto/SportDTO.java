package ru.itmo.hls1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SportDTO {
    private long sportId;
    private String sportType;
}
