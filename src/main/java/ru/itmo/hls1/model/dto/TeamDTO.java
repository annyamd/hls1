package ru.itmo.hls1.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

import static ru.itmo.hls1.controllers.util.ValidationMessages.MSG_ID_NEGATIVE;
import static ru.itmo.hls1.controllers.util.ValidationMessages.MSG_SIZE_NEGATIVE;

@Data
@AllArgsConstructor
public class TeamDTO {

    @Min(value = 0, message = MSG_ID_NEGATIVE)
    private Long teamId;

    @NotBlank(message = "firstName field can't be blank")
    private String teamName;

    @Min(value = 0, message = MSG_ID_NEGATIVE)
    @NotNull(message = "teamManagerId field can't be null")
    private Long teamManagerId;

    @Min(value = 0, message = MSG_SIZE_NEGATIVE)
    private Long teamSize;

    @NotNull(message = "isFreeToJoin field can't be null")
    private Boolean isFreeToJoin;

    private Set<Long> playersId;
}
