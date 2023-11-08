package ru.itmo.hls1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls1.model.dto.BookingRecordDTO;
import ru.itmo.hls1.sevice.PlaygroundService;
import ru.itmo.hls1.model.dto.TeamDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/player")
public class PlayerController {
//    see all his records, teams, joining to teams
//    create a team and become a team manager
//    team members can see schedule

    private final PlaygroundService playgroundService;

    @PostMapping(value = "/record/make", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> makeBookingRecord(@RequestBody BookingRecordDTO newRecord) {
        String login = newRecord.getLogin();
//              teamService.addUserToTeam(teamId, userId) (if (team.is_free_to_join && team.size < team.MAX_SIZE) )
//              check result
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/team/join")
    public ResponseEntity<?> joinTeam(@RequestParam int teamId, @RequestParam String login) {
//              teamService.addUserToTeam(teamId, userId) (if (team.is_free_to_join && team.size < team.MAX_SIZE) )
//              check result
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/team/create", consumes = "application/json")
    public ResponseEntity<?> createTeam(@RequestBody TeamDTO teamDTO) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/record/cancel", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> deleteBookingRecord(@RequestBody BookingRecordDTO newRecord) {
        String login = newRecord.getLogin();
//              teamService.addUserToTeam(teamId, userId) (if (team.is_free_to_join && team.size < team.MAX_SIZE) )
//              check result
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/team/quit")
    public ResponseEntity<?> quitTeam(@RequestParam int teamId, @RequestParam String login) {
//              teamService.addUserToTeam(teamId, userId) (if (team.is_free_to_join && team.size < team.MAX_SIZE) )
//              check result
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/records")
    public List<BookingRecordDTO> getUserRecords() {
        return playgroundService.getBookingRecordsByUser("me");
    }

    @GetMapping(value = "/teams")
    public List<Long> getAllUserJoinedTeams(@RequestParam String login) {
        return playgroundService.getUserTeamsIds(login);
    }


}
