package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return new UserDto(1L, "Mateusz", "unblocked", 3213L);
    }

    @PutMapping("/{id}/block")
    public UserDto blockUser(@PathVariable() Long id) {
        return new UserDto(id, "Matusz", "blocked", 31231L);
    }

    @PostMapping("/{id}/generateKey")
    public Long generateKey(@PathVariable() Long id) {
        return 198282L;
    }
}
