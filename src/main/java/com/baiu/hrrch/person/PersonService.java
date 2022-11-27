package com.baiu.hrrch.person;

import com.baiu.hrrch.group.Group;

import java.util.Collection;

public interface PersonService {
    Person createPerson(Person person);

    Person getPerson(Person person);

    Person updatePerson(Person person);

    void deletePerson(Person person);

    Collection<Person> getAllPersons();

    /**
     * Поиск пользователя по логину
     *
     * @param login Логин пользователя
     * @return Пользователь
     */
    Person getByLogin(String login);

    /**
     * Поиск пользователей по группе
     *
     * @param group Группа
     * @return коллекция пользователей состоящих в данной группе
     */
    Collection<Person> getPersonsByGroup(Group group);
}
