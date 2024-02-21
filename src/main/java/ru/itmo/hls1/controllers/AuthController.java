package ru.itmo.hls1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.hls1.model.dto.UserDTO;
import ru.itmo.hls1.sevice.AuthService;


@Validated
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@Validated @RequestBody UserDTO userDTO) {
        return authService.signIn(userDTO);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Validated @RequestBody UserDTO userDTO) {
        return authService.signUp(userDTO);
    }
}
