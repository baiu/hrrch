package com.baiu.hrrch.task;

import com.baiu.hrrch.exception.EntityNotFoundException;
import com.baiu.hrrch.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task get(Task task) {
        return taskRepository.findById(task.getId())
                .orElseThrow(() -> new EntityNotFoundException(task.getClass(), task.getId()));
    }

    @Override
    public Task update(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void delete(Task task) {
        task.setActive(false);
        taskRepository.save(task);
    }

    @Override
    public List<Task> getTasksByExecutorPerson(Person person) {
        return taskRepository.getTasksByExecutorPerson(person);
    }
}
