package com.baiu.hrrch.task;

import com.baiu.hrrch.person.Person;

import java.util.List;

public interface ProcessService {
    Process create(Process process);

    Process get(Process process);

    Process update(Process process);

    void delete(Process process);

    List<Process> getProcessesByCreatePerson(Person person);
}
