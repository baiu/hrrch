package com.baiu.hrrch.dictionary;

import com.baiu.hrrch.person.Person;

import java.util.List;

public interface DictionaryService {
    Dictionary createDictionary(Person person, Dictionary dic);

    Dictionary getDictionary(Dictionary dic);

    Dictionary updateDictionary(Person person, Dictionary dic);

    void deleteDictionary(Person person, Dictionary dic);

    List<Dictionary> getAllCatalogs(Person person);
}
