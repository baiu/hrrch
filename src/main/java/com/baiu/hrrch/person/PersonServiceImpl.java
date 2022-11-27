package com.baiu.hrrch.person;

import com.baiu.hrrch.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.baiu.hrrch.group.Group;

import java.util.Collection;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Person createPerson(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

    @Override
    public Person getPerson(Person person) {
        return personRepository.findById(person.getId())
                .orElseThrow(() -> new EntityNotFoundException(person.getClass(), person.getId()));
    }

    @Override
    public Person updatePerson(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

    @Override
    public void deletePerson(Person person) {
        person.setActive(false);
        personRepository.save(person);
    }

    @Override
    public Collection<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getByLogin(String login) {
        return personRepository.findByLogin(login);
    }

    @Override
    public Collection<Person> getPersonsByGroup(Group group) {
        return personRepository.findAllByGroups(group);
    }
}
