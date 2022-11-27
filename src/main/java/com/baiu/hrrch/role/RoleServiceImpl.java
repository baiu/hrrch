package com.baiu.hrrch.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baiu.hrrch.exception.EntityNotFoundException;
import com.baiu.hrrch.person.Person;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRole(Role role) {
        return roleRepository.findById(role.getId())
                .orElseThrow(() -> new EntityNotFoundException(role.getClass(), role.getId()));
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> getAllRolesByPerson(Person person) {
        return roleRepository.getAllRolesForPerson(person);
    }

    @Override
    public boolean isUserInRole(Person user, Role role) {
        return true;
    }

    @Override
    public Role getByName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
