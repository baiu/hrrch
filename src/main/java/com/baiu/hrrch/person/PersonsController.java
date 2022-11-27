package com.baiu.hrrch.person;

import com.baiu.hrrch.catalog.CatalogDto;
import com.baiu.hrrch.catalog.CatalogService;
import com.baiu.hrrch.role.Role;
import com.baiu.hrrch.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.baiu.hrrch.group.Group;
import com.baiu.hrrch.group.GroupService;

import java.util.Collection;
import java.util.List;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping("/api/persons")
public class PersonsController {
    private final PersonService personService;
    private final RoleService roleService;
    private final GroupService groupService;
    private final CatalogService catalogService;


    @Autowired
    public PersonsController(PersonService personService, RoleService roleService, GroupService groupService, CatalogService catalogService) {
        this.personService = personService;
        this.roleService = roleService;
        this.groupService = groupService;
        this.catalogService = catalogService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Person create(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{personId}")
    public Person getById(@PathVariable Long personId) {
        return personService.getPerson(new Person(personId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public Person update(@RequestBody Person person) {
        return personService.updatePerson(person);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{personId}")
    public void delete(@PathVariable Long personId) {
        personService.deletePerson(new Person(personId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Collection<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{personId}/roles")
    public List<Role> getAllRolesByPerson(@PathVariable Long personId) {
        return roleService.getAllRolesByPerson(new Person(personId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{personId}/catalogs")
    public List<CatalogDto> getAllCatalogsForPerson(@PathVariable Long personId) {
        return catalogService.getAllCatalogsByPerson(new Person(personId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{personId}/groups")
    public Collection<Group> getAllGroupsByPerson(@PathVariable Long personId) {
        return groupService.getByPerson(new Person(personId));
    }
}
