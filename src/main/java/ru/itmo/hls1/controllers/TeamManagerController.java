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
import ru.itmo.hls1.model.dto.TeamManagerDTO;
import ru.itmo.hls1.sevice.TeamManagerService;
import ru.itmo.hls1.sevice.TeamService;

import java.util.List;

import static ru.itmo.hls1.controllers.util.ValidationMessages.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/manager")
public class TeamManagerController {

    private final TeamManagerService teamManagerService;
    private final TeamService teamService;

    @GetMapping(value = "/")
    public ResponseEntity<?> getAllTeamManagers(@RequestParam(value = "page", defaultValue = "0") @Min(value = 0, message = MSG_PAGE_NEGATIVE) int page,
                                                @RequestParam(value = "size", defaultValue = "5") @Min(value = 0, message = MSG_SIZE_NEGATIVE) @Max(value = 50, message = MSG_SIZE_TOO_BIG) int size
    ) {
        List<TeamManagerDTO> teamManagerDTOs = teamManagerService.findAll(page, size);
        return ResponseEntity.ok(teamManagerDTOs);
    }

    @GetMapping(value = "/{managerId}")
    public ResponseEntity<?> getTeamManagerById(
            @PathVariable @Min(value = 0, message = MSG_ID_NEGATIVE) long managerId
    ) {
        TeamManagerDTO teamManagerDTO = teamManagerService.findById(managerId);
        return ResponseEntity.ok(teamManagerDTO);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> createTeamManager(@Valid @RequestBody TeamManagerDTO managerDto) {
        TeamManagerDTO created = teamManagerService.create(managerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping(value = "/{managerId}")
    public ResponseEntity<?> deleteTeamManager(
            @PathVariable @Min(value = 0, message = MSG_ID_NEGATIVE) long managerId
    ) {
        teamManagerService.delete(managerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{managerId}")
    public ResponseEntity<?> updateTeamManager(
            @PathVariable @Min(value = 0, message = MSG_ID_NEGATIVE) long managerId,
            @Valid @RequestBody TeamManagerDTO managerDto
    ) {
        TeamManagerDTO teamManagerDTO = teamManagerService.update(managerId, managerDto);
        return ResponseEntity.ok(teamManagerDTO);
    }

    //    only by its team manager
//    @PostMapping(value = "/{managerId}/teams/{playerId}")
//    public ResponseEntity<?> addMemberToTeam(
//            @PathVariable @Min(value = 0, message = MSG_ID_NEGATIVE) long teamId,
//            @PathVariable @Min(value = 0, message = MSG_ID_NEGATIVE) long playerId,
//            @RequestParam @Min(value = 0, message = MSG_ID_NEGATIVE) long managerId
//    ) {
//        TeamDTO updated = teamService.addMember(teamId, playerId, managerId);
//        return ResponseEntity.ok(updated);
//    }
//
//    @DeleteMapping(value = "/{managerId}/players/{playerId}")
//    public ResponseEntity<?> removeMemberFromTeam(
//            @PathVariable @Min(value = 0, message = MSG_ID_NEGATIVE) long teamId,
//            @PathVariable @Min(value = 0, message = MSG_ID_NEGATIVE) long playerId,
//            @RequestParam @Min(value = 0, message = MSG_ID_NEGATIVE) long managerId
//    ) {
//        TeamDTO updated = teamService.removeMember(teamId, playerId, managerId);
//        return ResponseEntity.ok(updated);
//    }

}
