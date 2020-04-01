package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public UserDto create(@RequestBody UserDto userDto) {
        return new UserDto(1L, "Mateusz", "unblocked", 3213L);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/block/{id}")
    public UserDto block(@PathVariable Long id) {
        return new UserDto(id, "Matusz", "blocked", 31231L);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/generateKey/{id}")
    public Long generateKey(@PathVariable Long id) {
        return 123L;
    }
}