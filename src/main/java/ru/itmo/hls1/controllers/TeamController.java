package ru.itmo.hls1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls1.model.dto.TeamDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/teams")
public class TeamController {
//  deleteTeam
//  change booking for his team
//  creates team (choosing players from list) and removing, make booking

// get teams of created by user and get teams user is joined
//if chosen team made by team manager

    @GetMapping(value = "/")
    public ResponseEntity<?> getAllTeams(@RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "size", defaultValue = "10") int size) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value="/{teamId}")
    public ResponseEntity<?> getTeamById(@PathVariable long teamId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> createTeam(@RequestBody TeamDTO teamDTO) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable long teamId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{teamId}")
    public ResponseEntity<?> updateTeam(@PathVariable long teamId, @RequestBody TeamDTO teamDTO) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PutMapping(value = "/add_player/{id}")
//    public ResponseEntity<?> addTeamPlayer(@PathVariable long id, @RequestBody TeamDTO teamDTO) {
////        if chosen team made by team manager
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//
//    @PostMapping(value = "{playerId}/teams/{teamId}")
//    public ResponseEntity<?> joinTeam(@PathVariable long teamId, @PathVariable long playerId) {
//        return new ResponseEntity<>(HttpStatus.ACCEPTED);
//    }
//
//    @DeleteMapping(value = "{playerId}/teams/{teamId}")
//    public ResponseEntity<?> leaveTeam(@PathVariable long teamId, @PathVariable long playerId) {
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/{playerId}/teams")
//    public List<Long> getPlayerJoinedTeams(@PathVariable long playerId) {
//        return playgroundService.getUserTeamsIds(playerId);
//    }

}
