package com.baiu.hrrch.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baiu.hrrch.exception.EntityNotFoundException;
import com.baiu.hrrch.person.Person;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {
    private final DictionaryRepository dictionaryRepository;

    @Autowired
    public DictionaryServiceImpl(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    @Override
    public Dictionary createDictionary(Person person, Dictionary dic) {
        return dictionaryRepository.save(dic);
    }

    @Override
    public Dictionary getDictionary(Dictionary dic) {
        return dictionaryRepository.findById(dic.getId())
                .orElseThrow(() -> new EntityNotFoundException(dic.getClass(), dic.getId()));
    }

    @Override
    public Dictionary updateDictionary(Person person, Dictionary dic) {
        return dictionaryRepository.save(dic);
    }

    @Override
    public void deleteDictionary(Person person, Dictionary dic) {
        dic.setActive(false);
        dictionaryRepository.save(dic);
    }

    @Override
    public List<Dictionary> getAllCatalogs(Person person) {
        return dictionaryRepository.findAll();
    }
}
