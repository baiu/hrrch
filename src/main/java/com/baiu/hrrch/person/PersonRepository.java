package com.baiu.hrrch.person;

import org.springframework.data.jpa.repository.JpaRepository;
import com.baiu.hrrch.group.Group;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByLogin(String login);

    List<Person> findAllByGroups(Group group);
}
