package ru.itmo.hls1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamManagerDTO {
    private long id;
    private long userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
}
