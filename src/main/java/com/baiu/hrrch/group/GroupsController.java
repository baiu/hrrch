package com.baiu.hrrch.group;

import com.baiu.hrrch.person.Person;
import com.baiu.hrrch.person.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping("/api/groups")
public class GroupsController {
    private final GroupService groupService;
    private final GroupMapper groupMapper;
    private final PersonService personService;

    public GroupsController(GroupService groupService, GroupMapper groupMapper, PersonService personService) {
        this.groupService = groupService;
        this.groupMapper = groupMapper;
        this.personService = personService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Group create(@RequestBody Group group) {
        return groupService.createGroup(group);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{groupId}")
    public Group getById(@PathVariable(required = true) Long groupId) {
        return groupService.getGroup(new Group(groupId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public Group update(@RequestBody Group group) {
        return groupService.updateGroup(group);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{groupId}")
    public void delete(@PathVariable Long groupId) {
        groupService.deleteGroup(new Group(groupId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<GroupDto> getAll() {
        return groupMapper.mapAsList(groupService.getAllGroups(), GroupDto.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{groupId}/persons")
    public Collection<Person> getAllPersonsByGroup(@PathVariable Long groupId) {
        return personService.getPersonsByGroup(new Group(groupId));
    }
}
