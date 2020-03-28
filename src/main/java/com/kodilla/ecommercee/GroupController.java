package com.kodilla.ecommercee;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/group")
public class GroupController {

    @RequestMapping(method = RequestMethod.GET, value = "getGroupsList")
    public List<String> getGroupsList() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createGroup", consumes = APPLICATION_JSON_VALUE)
    public String createGroup(@RequestBody String groupDto) {
        return "groupDto";
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGroup")
    public String getGroup(@RequestParam Integer Id) {
        return "groupDto";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateGroup")
    public String updateGroup(@RequestBody String updatedGroupDto) {
        return "updatedGroupDto";
    }
}
