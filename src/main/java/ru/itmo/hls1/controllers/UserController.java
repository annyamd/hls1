package ru.itmo.hls1.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls1.model.dto.RoleDTO;
import ru.itmo.hls1.model.dto.UserDTO;
import ru.itmo.hls1.sevice.UserService;

import java.util.List;

import static ru.itmo.hls1.controllers.util.ValidationMessages.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/")
    public ResponseEntity<?> getAllUsers(
            @RequestParam(value = "page", defaultValue = "0") @Min(value = 0, message = MSG_PAGE_NEGATIVE) int page,
            @RequestParam(value = "size", defaultValue = "5") @Min(value = 0, message = MSG_SIZE_NEGATIVE) @Max(value = 50, message = MSG_SIZE_TOO_BIG) int size
    ) {
        List<UserDTO> userDTOs = userService.findAll(page, size);
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<?> getUserById(
            @PathVariable @Min(value = 0, message = MSG_ID_NEGATIVE) long userId
    ) {
        UserDTO user = userService.findById(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/")
    @PreAuthorize("hasRole('SUPERVISOR')")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO user) {
        UserDTO created = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{userId}/roles")
    public ResponseEntity<?> addRoleToUser(
            @PathVariable @Min(value = 0, message = MSG_ID_NEGATIVE) long userId
    ) {
        List<RoleDTO> roles = userService.getRoles(userId);
        return ResponseEntity.ok(roles);
    }

    @PutMapping("/{userId}/roles")
    @PreAuthorize("hasRole('SUPERVISOR')")
    public ResponseEntity<?> addRoleToUser(
            @PathVariable @Min(value = 0, message = MSG_ID_NEGATIVE) long userId,
            @Valid @RequestBody RoleDTO role
    ) {
        userService.addRole(userId, role);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/roles")
    @PreAuthorize("hasRole('SUPERVISOR')")
    public ResponseEntity<?> removeRoleFromUser(
            @PathVariable @Min(value = 0, message = MSG_ID_NEGATIVE) long userId,
            @Valid @RequestBody RoleDTO role
    ) {
        userService.removeRole(userId, role);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
