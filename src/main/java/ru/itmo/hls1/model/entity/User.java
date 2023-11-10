package ru.itmo.hls1.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
//@Getter
//@Setter
public class User {
    private long user_id;
    private String username;
    private String password;
}
