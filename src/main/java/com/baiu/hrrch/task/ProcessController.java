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
@RequestMapping("/api/processes")
public class ProcessController {
    private final ProcessService processService;

    @Autowired
    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Process create(@RequestBody Process process) {
        return processService.create(process);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{processId}")
    public Process get(@PathVariable Long processId) {
        return processService.get(new Process(processId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public Process update(@RequestBody Process process) {
        return processService.update(process);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{processId}")
    public void delete(@PathVariable Long processId) {
        processService.delete(new Process(processId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{createPersonId}")
    public List<Process> getProcessesByCreatePerson(@PathVariable Long createPersonId) {
        return processService.getProcessesByCreatePerson(new Person(createPersonId));
    }
}
