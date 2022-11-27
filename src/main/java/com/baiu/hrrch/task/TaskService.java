package com.baiu.hrrch.task;

import com.baiu.hrrch.person.Person;

import java.util.List;

public interface TaskService {
    Task create(Task task);

    Task get(Task task);

    Task update(Task task);

    void delete(Task task);

    List<Task> getTasksByExecutorPerson(Person person);
}
