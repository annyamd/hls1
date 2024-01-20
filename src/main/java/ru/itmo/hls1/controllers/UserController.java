package ru.itmo.hls1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls1.model.dto.UserDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {
    @GetMapping(value = "/")
    public ResponseEntity<?> getAllTeams(@RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "size", defaultValue = "10") int size) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value="/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable long userId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    supervisor only
    @PostMapping(value = "/")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDto) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    changing roles

}
