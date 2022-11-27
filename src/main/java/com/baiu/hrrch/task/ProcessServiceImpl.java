package com.baiu.hrrch.task;

import com.baiu.hrrch.exception.EntityNotFoundException;
import com.baiu.hrrch.person.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessServiceImpl implements ProcessService {

    private final ProcessRepository processRepository;

    public ProcessServiceImpl(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    @Override
    public Process create(Process process) {
        return processRepository.save(process);
    }

    @Override
    public Process get(Process process) {
        return processRepository.findById(process.getId())
                .orElseThrow(() -> new EntityNotFoundException(process.getClass(), process.getId()));
    }

    @Override
    public Process update(Process process) {
        return processRepository.save(process);
    }

    @Override
    public void delete(Process process) {
        processRepository.delete(process);
    }

    @Override
    public List<Process> getProcessesByCreatePerson(Person person) {
        return processRepository.getProcessesByCreatePerson(person);
    }
}
