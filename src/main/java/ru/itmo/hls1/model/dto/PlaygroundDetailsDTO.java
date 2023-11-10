package ru.itmo.hls1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaygroundDetailsDTO {
    private PlaygroundDTO playground;
//    private PlaygroundAvailabilityDTO availability;
//    private ... available time, booking, sport
}
