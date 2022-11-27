package com.baiu.hrrch.task;

import com.baiu.hrrch.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskService.create(task);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{taskId}")
    public Task get(@PathVariable Long taskId) {
        return taskService.get(new Task(taskId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public Task update(@RequestBody Task task) {
        return taskService.update(task);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{taskId}")
    public void delete(@PathVariable Long taskId) {
        taskService.delete(new Task(taskId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{executorPersonId}")
    public List<Task> getTasksByExecutorPerson(@PathVariable Long executorPersonId) {
        return taskService.getTasksByExecutorPerson(new Person(executorPersonId));
    }

}
