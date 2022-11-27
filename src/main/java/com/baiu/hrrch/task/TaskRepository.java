package com.baiu.hrrch.task;

import com.baiu.hrrch.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> getTasksByExecutorPerson(Person person);
}
