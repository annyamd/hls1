package ru.itmo.hls1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private long userId;
    private String login;
    private String password;
}
