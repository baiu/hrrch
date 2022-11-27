package com.baiu.hrrch.group;

import com.baiu.hrrch.person.Person;

import java.util.Set;

public class GroupDto {
    private Long id;
    private String description;
    private Set<Person> persons;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}
