package com.baiu.hrrch.group;

import com.baiu.hrrch.catalog.Catalog;
import com.baiu.hrrch.person.Person;
import com.baiu.hrrch.role.Role;

import java.util.Collection;
import java.util.List;

public interface GroupService {
    Group createGroup(Group group);

    Group getGroup(Group group);

    Group updateGroup(Group group);

    void deleteGroup(Group group);

    List<Group> getAllGroups();

    /**
     * Добавить каталог в группу
     *
     * @param role  Роль
     * @param group Группа
     */
    Group addRoleToGroup(Group group, Role role);

    /**
     * Удалить каталог из группы
     *
     * @param role   Роль
     * @param group  Группа
     */
    Group removeRoleFromGroup(Group group, Role role);

    /**
     * Поиск групп для пользователя
     *
     * @param person Пользовать
     * @return Список групп
     */
    Collection<Group> getByPerson(Person person);

    /**
     * Поиск групп для каталога и всех его предков
     *
     * @param person  Пользовать, вызвавший операцию
     * @param catalog Каталог для кого ведется поиск
     * @return Список групп
     */
    Collection<Group> getByCatalogAndAncestors(Person person, Catalog catalog);

    /**
     * Добавить пользователя в группу
     *  @param person Пользователь которого добавляют
     * @param group  Группа в которую добавляют
     * @return
     */
    Group addPersonToGroup(Person person, Group group);

    /**
     * Удалить пользователя из группы
     *
     * @param person Пользователь которого удаляют
     * @param group  Группа из которой удаляют
     */
    Group removePersonFromGroup(Person person, Group group);
}
