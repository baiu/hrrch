package com.baiu.hrrch.role;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping("/api/roles")
public class RolesController {
    private final RoleService roleService;

    public RolesController(RoleService roleService) {
        this.roleService = roleService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{roleId}")
    public Role getById(@PathVariable(required = true) Long roleId) {
        return roleService.getRole(new Role(roleId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Role> getAll() {
        return roleService.getAllRoles();
    }
}
