package com.baiu.hrrch.group;

import com.baiu.hrrch.groupcatalog.GroupCatalog;
import com.baiu.hrrch.person.Person;
import com.baiu.hrrch.role.Role;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Описание
     */
    private String description;

    /**
     * Роли
     */
    @ManyToMany
    @JoinTable(name = "group_role",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    /**
     * Пользователи
     */
    @ManyToMany()
    @JoinTable(name = "group_person",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private Set<Person> persons = new HashSet<>();

    /**
     * Каталоги
     */
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private Set<GroupCatalog> groupCatalogs = new HashSet<>();

    public Group() {

    }

    public Group(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> members) {
        this.persons = members;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<GroupCatalog> getGroupCatalogs() {
        return groupCatalogs;
    }

    public void setGroupCatalogs(Set<GroupCatalog> groupCatalogs) {
        this.groupCatalogs = groupCatalogs;
    }
}
