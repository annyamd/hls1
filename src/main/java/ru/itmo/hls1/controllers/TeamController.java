package ru.itmo.hls1.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls1.model.dto.TeamDTO;
import ru.itmo.hls1.sevice.TeamService;

import java.util.List;

import static ru.itmo.hls1.controllers.util.ValidationMessages.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/teams")
public class TeamController {
//  deleteTeam
//  change booking for his team
//  creates team (choosing players from list) and removing, make booking

// get teams of created by user and get teams user is joined
//if chosen team made by team manager

    private final TeamService teamService;

    @GetMapping(value = "/")
    public ResponseEntity<?> getAllTeams(@RequestParam(value = "page", defaultValue = "0") @Min(value = 0, message = MSG_PAGE_NEGATIVE) int page,
                                         @RequestParam(value = "size", defaultValue = "5") @Min(value = 0, message = MSG_SIZE_NEGATIVE) @Max(value = 50, message = MSG_SIZE_TOO_BIG) int size
    ) {
        List<TeamDTO> teamDTOs = teamService.findAll(page, size);
        return ResponseEntity.ok(teamDTOs);
    }

    @GetMapping(value = "/{teamId}")
    public ResponseEntity<?> getTeamById(
            @PathVariable @Min(value = 0, message = MSG_ID_NEGATIVE) long teamId
    ) {
        TeamDTO teamDTO = teamService.findById(teamId);
        return ResponseEntity.ok(teamDTO);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> createTeam(@Valid @RequestBody TeamDTO teamDTO) {
        TeamDTO created = teamService.create(teamDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping(value = "/{teamId}")
    public ResponseEntity<?> deleteTeam(
            @PathVariable @Min(value = 0, message = MSG_ID_NEGATIVE) long teamId
    ) {
        teamService.delete(teamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{teamId}")
    public ResponseEntity<?> updateTeam(
            @PathVariable @Min(value = 0, message = MSG_ID_NEGATIVE) long teamId,
            @Valid @RequestBody TeamDTO teamDTO
    ) {
        TeamDTO updated = teamService.update(teamId, teamDTO);
        return ResponseEntity.ok(updated);
    }

}
