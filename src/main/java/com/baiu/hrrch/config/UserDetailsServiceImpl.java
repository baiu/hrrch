package com.baiu.hrrch.config;

import com.baiu.hrrch.person.Person;
import com.baiu.hrrch.person.PersonService;
import com.baiu.hrrch.role.Role;
import com.baiu.hrrch.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonService personService;
    private final RoleService roleService;

    @Autowired
    public UserDetailsServiceImpl(PersonService personService, RoleService roleService) {
        this.personService = personService;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personService.getByLogin(username);

        if (person == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        List<Role> allRolesByPerson = roleService.getAllRolesByPerson(person);

        List<SimpleGrantedAuthority> authorities = allRolesByPerson.stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());

        return new User(person.getLogin(), person.getPassword(), authorities);
    }
}
