package ru.itmo.hls1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls1.model.dto.SportDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/sports")
public class SportController {
    @GetMapping(value = "/")
    public ResponseEntity<?> getAllSports(@RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "size", defaultValue = "10") int size) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value="/{sportId}")
    public ResponseEntity<?> getSportById(@PathVariable long sportId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> createSport(@RequestBody SportDTO sportDto) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{sportId}")
    public ResponseEntity<?> deleteSport(@PathVariable long sportId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
