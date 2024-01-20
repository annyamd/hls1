package ru.itmo.hls1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls1.model.dto.BookingRecordDTO;
import ru.itmo.hls1.model.dto.PlayerDto;
import ru.itmo.hls1.sevice.PlaygroundService;
import ru.itmo.hls1.model.dto.TeamDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/players")
public class PlayerController {

    private final PlaygroundService playgroundService;

    @GetMapping(value = "/")
    public ResponseEntity<?> getAllPlayers(@RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "size", defaultValue = "10") int size) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{playerId}")
    public ResponseEntity<?> getPlayerById(@PathVariable long playerId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    supervisor role
    @PostMapping
    public ResponseEntity<?> createPlayer(@RequestBody PlayerDto playerDto) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //    supervisor role
    @PutMapping("/{playerId}")
    public ResponseEntity<?> updatePlayer(@PathVariable long playerId, @RequestBody PlayerDto playerDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<?> deletePlayer(@PathVariable long playerId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
