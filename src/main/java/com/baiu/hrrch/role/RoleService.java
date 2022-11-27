package com.baiu.hrrch.role;

import com.baiu.hrrch.person.Person;

import java.util.List;

public interface RoleService {

    Role createRole(Role role);

    Role getRole(Role role);

    Role updateRole(Role role);

    void deleteRole(Role role);

    List<Role> getAllRoles();

    /**
     * Все роли пользователя
     *
     * @param person Пользователь
     * @return Возвращает список ролей
     */
    List<Role> getAllRolesByPerson(Person person);

    /**
     * Включен ли пользователь
     * в указанную логическую «роль»
     *
     * @param person Пользователь
     * @param role   Роль
     * @return Возвращает true если включен.
     */
    boolean isUserInRole(Person person, Role role);

    /**
     * Поиск роли по названию
     *
     * @param roleName Название роли
     * @return Роль
     */
    Role getByName(String roleName);
}
