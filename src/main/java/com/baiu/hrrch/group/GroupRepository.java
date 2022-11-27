package com.baiu.hrrch.group;

import com.baiu.hrrch.person.Person;
import com.baiu.hrrch.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findAllByPersons(Person person);

    @Query("select (count(g)>0) from groups g join g.persons p where g = :group and p =:person")
    boolean existsByGroupAndPerson(@Param("person") Person person, @Param("group") Group group);

    @Query("select (count(g)>0) from groups g join g.roles r where g = :group and r =:role")
    boolean existsByGroupAndRole(@Param("group") Group group, @Param("role") Role role);
}
