package com.baiu.hrrch.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.baiu.hrrch.person.Person;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    @Query("SELECT distinct r FROM role r join r.groups g join g.persons p WHERE p = :person")
    List<Role> getAllRolesForPerson(@Param("person") Person person);
}
