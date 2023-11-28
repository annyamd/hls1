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
@RequestMapping(path = "/players")
public class PlayerController {

    private final PlaygroundService playgroundService;

    @PostMapping(value = "/{playerId}/record", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> makeBookingRecord(@PathVariable long playerId, @RequestBody BookingRecordDTO newRecord) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{playerId}/record", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> deleteBookingRecord(@PathVariable long playerId, @RequestBody BookingRecordDTO newRecord) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{playerId}/record", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> changeBookingRecord(@PathVariable long playerId, @RequestBody BookingRecordDTO newRecord) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "{userId}/records")
    public List<BookingRecordDTO> getUserRecords(@PathVariable long userId) {
        return playgroundService.getBookingRecordsByUser("me");
    }

    @PostMapping(value = "{playerId}/teams/{teamId}")
    public ResponseEntity<?> joinTeam(@PathVariable long teamId, @PathVariable long playerId) {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "{playerId}/teams/{teamId}")
    public ResponseEntity<?> leaveTeam(@PathVariable long teamId, @PathVariable long playerId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/{playerId}/teams/new", consumes = "application/json")
    public ResponseEntity<?> createTeam(@PathVariable long playerId, @RequestBody TeamDTO teamDTO) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{playerId}/teams")
    public List<Long> getPlayerJoinedTeams(@PathVariable long playerId) {
        return playgroundService.getUserTeamsIds(playerId);
    }

    @GetMapping(value = "/")
    public List<Long> getPlayersWithFilter(@RequestParam String name, @RequestParam int age) {
        return null;
    }


//    @GetMapping(value = "/")
//    public List<Long> getPlayersByAge(@RequestParam int age) {
//        return null;
//    }

//    @GetMapping(value = "/")
//    public List<Long> getPlayersByAnotherFilter(@RequestParam int age) {
////        return playgroundService.getUserTeamsIds(playerId);
//        return null;
//    }


}
