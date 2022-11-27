package com.baiu.hrrch.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.baiu.hrrch.group.Group;
import com.baiu.hrrch.person.Person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    private LocalDateTime createDate;
    @ManyToOne
    @JsonIgnore
    private Person createPerson;

    /**
     * Наименование роли
     */
    private String name;

    /**
     * Описание роли
     */
    private String description;

    /**
     * Группы
     */
    @ManyToMany
    @JoinTable(name = "group_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    @JsonIgnore
    private Set<Group> groups;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Person getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(Person createPerson) {
        this.createPerson = createPerson;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
