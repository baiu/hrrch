package com.baiu.hrrch.task;

import com.baiu.hrrch.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcessRepository extends JpaRepository<Process, Long> {
    List<Process> getProcessesByCreatePerson(Person person);
}
