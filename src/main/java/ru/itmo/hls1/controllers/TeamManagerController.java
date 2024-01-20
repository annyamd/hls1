package ru.itmo.hls1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls1.model.dto.TeamManagerDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/manager")
public class TeamManagerController {

    @GetMapping(value = "/")
    public ResponseEntity<?> getAllTeamManagers(@RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "size", defaultValue = "10") int size) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value="/{managerId}")
    public ResponseEntity<?> getTeamManagerById(@PathVariable long managerId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> createTeamManager(@RequestBody TeamManagerDTO managerDto) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{managerId}")
    public ResponseEntity<?> deleteTeamManager(@PathVariable long managerId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{managerId}")
    public ResponseEntity<?> updateTeamManager(@PathVariable long managerId, @RequestBody TeamManagerDTO managerDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
