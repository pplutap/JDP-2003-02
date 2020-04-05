package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.GroupDefinitionException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/groups")
public class GroupController {

    @GetMapping
    public List<GroupDto> get() {
        return Arrays.asList(new GroupDto(1L, "Shoes"), new GroupDto(2L, "Pants"));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void create(@RequestBody GroupDto groupDto) {
    }

    @GetMapping("/{id}")
    public GroupDto get(@PathVariable Long id) throws GroupDefinitionException {
        return new GroupDto(1L, "Shoes");
    }

    @PutMapping
    public void update(@RequestBody GroupDto groupDto) {
    }
}
