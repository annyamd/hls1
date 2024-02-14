package ru.itmo.hls1.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Only for testing purpose
@RestController
public class HelloController {
    @GetMapping("/guest_hello")
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/plain_hello")
    public String helloPlainUser() {
        return "Hello user!";
    }

    @GetMapping("/admin_hello")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloAdminUser() {
        return "Hello admin!";
    }
}
