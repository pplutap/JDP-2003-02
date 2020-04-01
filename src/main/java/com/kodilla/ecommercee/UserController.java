package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return new UserDto(1L, "Mateusz", "unblocked", 3213L);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "block/{userId}")
    public UserDto blockUser(@PathVariable() Long userId) {
        return new UserDto(userId, "Matusz", "blocked", 31231L);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/generateKey/{userKey}")
    public Long generateKey(@PathVariable() Long userKey) {
        return userKey;
    }
}