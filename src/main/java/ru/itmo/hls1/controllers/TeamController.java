package ru.itmo.hls1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls1.model.dto.BookingRecordDTO;
import ru.itmo.hls1.model.dto.TeamDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/teams")
public class TeamController {
//  deleteTeam
//  change booking for his team
//  creates team (choosing players from list) and removing, make booking

    @GetMapping(value="/{teamId}")
    public ResponseEntity<?> getTeamById(@PathVariable long teamId) {
//        if chosen team made by team manager
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value="user/{userId}")
    public ResponseEntity<?> getTeams(@PathVariable long userId) {
//        if chosen team made by team manager
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> createTeam(@RequestParam long teamId, @RequestParam long requestId) {
//        if chosen team made by team manager
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updateTeamInfo(@RequestParam long teamId, @RequestBody TeamDTO teamDTO) {
//        if chosen team made by team manager
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/add_player/{id}")
    public ResponseEntity<?> addTeamPlayer(@PathVariable long id, @RequestBody TeamDTO teamDTO) {
//        if chosen team made by team manager
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
