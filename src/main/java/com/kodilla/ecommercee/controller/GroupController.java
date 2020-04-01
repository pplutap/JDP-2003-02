package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.GroupDefinitionException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/group")
public class GroupController {

    @RequestMapping(method = RequestMethod.GET, value = "getGroupsList")
    public List<GroupDto> getGroupsList() {
        return Arrays.asList(new GroupDto(1L, "Shoes"), new GroupDto(2L, "Pants"));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createGroup", consumes = APPLICATION_JSON_VALUE)
    public void createGroup(@RequestBody GroupDto groupDto) {
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGroup")
    public GroupDto getGroup(@RequestParam Long id) throws GroupDefinitionException {
        return new GroupDto(1L,"Shoes");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateGroup")
    public void updateGroup(@RequestBody GroupDto groupDto) {
    }
}
