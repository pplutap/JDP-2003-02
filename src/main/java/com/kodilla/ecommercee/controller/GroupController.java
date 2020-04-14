package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/groups")
public class GroupController {
    private GroupMapper mapper;
    private GroupDbService dbService;

    @Autowired
    public GroupController(GroupMapper mapper, GroupDbService dbService) {
        this.mapper = mapper;
        this.dbService = dbService;
    }

    @GetMapping
    public List<GroupDto> get() {
        return mapper.mapToGroupsListDto(dbService.getGroups());
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void create(@RequestBody GroupDto groupDto) {
        dbService.saveGroup(mapper.mapToGroup(groupDto));
    }

    @GetMapping("/{id}")
    public GroupDto get(@PathVariable Long id) throws GroupNotFoundException {
        return mapper.mapToGroupDto(dbService.getGroup(id).orElseThrow(GroupNotFoundException::new));
    }

    @PutMapping
    public void update(@RequestBody GroupDto groupDto) {
        dbService.saveGroup(mapper.mapToGroup(groupDto));
    }
}
