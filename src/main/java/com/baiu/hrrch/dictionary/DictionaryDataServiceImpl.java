package com.baiu.hrrch.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baiu.hrrch.exception.EntityNotFoundException;
import com.baiu.hrrch.person.Person;

@Service
public class DictionaryDataServiceImpl implements DictionaryDataService {

    private final DictionaryDataRepository dictionaryDataRepository;

    @Autowired
    public DictionaryDataServiceImpl(DictionaryDataRepository dictionaryDataRepository) {
        this.dictionaryDataRepository = dictionaryDataRepository;
    }

    @Override
    public DictionaryData createDictionaryData(Person person, DictionaryData dictionaryData) {
        return dictionaryDataRepository.save(dictionaryData);
    }

    @Override
    public DictionaryData getDictionaryData(DictionaryData dictionaryData) {
        return dictionaryDataRepository.findById(dictionaryData.getId())
                .orElseThrow(() -> new EntityNotFoundException(dictionaryData.getClass(), dictionaryData.getId()));
    }

    @Override
    public DictionaryData updateDictionaryData(Person person, DictionaryData dictionaryData) {
        return dictionaryDataRepository.save(dictionaryData);
    }

    @Override
    public void deleteDictionaryData(Person person, DictionaryData dictionaryData) {
        dictionaryDataRepository.delete(dictionaryData);
    }
}
