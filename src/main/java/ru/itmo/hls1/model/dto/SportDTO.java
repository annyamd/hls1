package ru.itmo.hls1.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import static ru.itmo.hls1.controllers.util.ValidationMessages.MSG_ID_NEGATIVE;

@Data
@AllArgsConstructor
public class SportDTO {

    @Min(value = 0, message = MSG_ID_NEGATIVE)
    private Long sportId;

    @NotNull(message = "sport_type field can't be null")
    @NotBlank(message = "sport_type field can't be blank")
    private String sportType;
}
