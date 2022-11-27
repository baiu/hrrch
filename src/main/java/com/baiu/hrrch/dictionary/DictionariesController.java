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

import java.util.List;

@RestController
@Secured("ROLE_MANAGER")
@RequestMapping("/api/dictionaries")
public class DictionariesController {
    private final DictionaryService dictionaryService;

    public DictionariesController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Dictionary create(@RequestBody Dictionary dictionary) {
        return dictionaryService.createDictionary(null, dictionary);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{dictionaryId}")
    public Dictionary getById(@PathVariable(required = true) Long dictionaryId) {
        return dictionaryService.getDictionary(new Dictionary(dictionaryId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public Dictionary update(@RequestBody Dictionary dictionary) {
        return dictionaryService.updateDictionary(null, dictionary);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{dictionaryId}")
    public void delete(@PathVariable Long dictionaryId) {
        dictionaryService.deleteDictionary(null, new Dictionary(dictionaryId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Dictionary> getAll() {
        return dictionaryService.getAllCatalogs(null);
    }
}
