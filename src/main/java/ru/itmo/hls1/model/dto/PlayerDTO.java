package ru.itmo.hls1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerDTO {
    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private Integer age;
    private Float heightCm;
    private Float weightKg;
    private String gender;
}
