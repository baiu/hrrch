package com.baiu.hrrch.group;

import com.baiu.hrrch.catalog.Catalog;
import com.baiu.hrrch.exception.CustomInternalServerException;
import com.baiu.hrrch.exception.EntityNotFoundException;
import com.baiu.hrrch.person.Person;
import com.baiu.hrrch.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group getGroup(Group group) {
        return groupRepository.findById(group.getId())
                .orElseThrow(() -> new EntityNotFoundException(group.getClass(), group.getId()));
    }

    @Override
    public Group updateGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public void deleteGroup(Group group) {
        groupRepository.delete(group);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group addRoleToGroup(Group group, Role role) {
        Long groupId = group.getId();
        group = groupRepository.findById(groupId).orElseThrow(() -> new EntityNotFoundException(Group.class, groupId));
        checkDuplicateRoles(role, group);
        group.getRoles().add(role);
        return groupRepository.save(group);
    }

    private void checkDuplicateRoles(Role role, Group group) {
        boolean isRoleInGroup = groupRepository.existsByGroupAndRole(group, role);
        if (isRoleInGroup) {
            throw new CustomInternalServerException("Роль уже присутствует в данной группе");
        }
    }

    @Override
    public Group removeRoleFromGroup(Group group, Role role) {
        Long groupId = group.getId();
        group = groupRepository.findById(groupId).orElseThrow(() -> new EntityNotFoundException(Group.class, groupId));
        checkExists(group, role);
        group.getRoles().remove(role);
        return groupRepository.save(group);
    }

    private void checkExists(Group group, Role role) {
        boolean isRoleInGroup = groupRepository.existsByGroupAndRole(group, role);
        if (!isRoleInGroup) {
            throw new CustomInternalServerException("У группы нет такой роли");
        }
    }

    @Override
    public Collection<Group> getByPerson(Person person) {
        return groupRepository.findAllByPersons(person);
    }

    @Override
    public Collection<Group> getByCatalogAndAncestors(Person person, Catalog catalog) {
        return null;
    }

    @Override
    public Group addPersonToGroup(Person person, Group group) {
        Long groupId = group.getId();
        group = groupRepository.findById(groupId).orElseThrow(() -> new EntityNotFoundException(Group.class, groupId));
        checkDuplicatePersons(person, group);
        group.getPersons().add(person);
        return groupRepository.save(group);
    }

    private void checkDuplicatePersons(Person person, Group group) {
        boolean isPersonInGroup = groupRepository.existsByGroupAndPerson(person, group);
        if (isPersonInGroup) {
            throw new CustomInternalServerException("Пользователь уже состоит в данной группе");
        }
    }

    @Override
    public Group removePersonFromGroup(Person person, Group group) {
        Long groupId = group.getId();
        group = groupRepository.findById(groupId).orElseThrow(() -> new EntityNotFoundException(Group.class, groupId));
        checkExists(person, group);
        group.getPersons().remove(person);
        return groupRepository.save(group);
    }

    private void checkExists(Person person, Group group) {
        boolean isPersonInGroup = groupRepository.existsByGroupAndPerson(person, group);
        if (!isPersonInGroup) {
            throw new CustomInternalServerException("Группа не содержит этого пользователя");
        }
    }
}
