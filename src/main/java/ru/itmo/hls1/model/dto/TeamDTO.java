package ru.itmo.hls1.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import static ru.itmo.hls1.controllers.util.ValidationMessages.MSG_ID_NEGATIVE;
import static ru.itmo.hls1.controllers.util.ValidationMessages.MSG_SIZE_NEGATIVE;

@Data
@AllArgsConstructor
public class TeamDTO {

    @Min(value = 0, message = MSG_ID_NEGATIVE)
    private Long teamId;

    @NotNull(message = "firstName field can't be null")
    private String teamName;

    @Min(value = 0, message = MSG_ID_NEGATIVE)
    @NotBlank(message = "teamManagerId field can't be blank")
    private Long teamManagerId;

    @Min(value = 0, message = MSG_SIZE_NEGATIVE)
    private Integer teamSize;

    @NotBlank(message = "isFreeToJoin field can't be blank")
    private Boolean isFreeToJoin;
}
