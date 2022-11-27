package com.baiu.hrrch.dictionary;

import com.baiu.hrrch.person.Person;

public interface DictionaryDataService {
    DictionaryData createDictionaryData(Person person, DictionaryData dictionaryData);

    DictionaryData getDictionaryData(DictionaryData dictionaryData);

    DictionaryData updateDictionaryData(Person person, DictionaryData dictionaryData);

    void deleteDictionaryData(Person person, DictionaryData dictionaryData);
}
