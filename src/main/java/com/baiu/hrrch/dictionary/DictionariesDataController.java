package com.baiu.hrrch.dictionary;

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

@RestController
@Secured("ROLE_USER")
@RequestMapping("/api/dictionaries_data")
public class DictionariesDataController {
    private final DictionaryDataService dictionaryDataService;

    public DictionariesDataController(DictionaryDataService dictionaryDataService) {
        this.dictionaryDataService = dictionaryDataService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public DictionaryData create(@RequestBody DictionaryData dictionaryData) {
        return dictionaryDataService.createDictionaryData(null, dictionaryData);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{dictionaryDataId}")
    public DictionaryData getById(@PathVariable(required = true) Long dictionaryDataId) {
        return dictionaryDataService.getDictionaryData(new DictionaryData(dictionaryDataId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public DictionaryData update(@RequestBody DictionaryData dictionaryData) {
        return dictionaryDataService.updateDictionaryData(null, dictionaryData);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{dictionaryDataId}")
    public void delete(@PathVariable Long dictionaryDataId) {
        dictionaryDataService.deleteDictionaryData(null, new DictionaryData(dictionaryDataId));
    }
}
