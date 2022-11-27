package com.baiu.hrrch.doc;

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
@Secured("ROLE_USER")
@RequestMapping("/api/docs")
public class DocsController {

    private final DocService docService;

    @Autowired
    public DocsController(DocService docService) {
        this.docService = docService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Doc create(@RequestBody Doc document) {
        return docService.createDoc(document);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Doc getById(@PathVariable String id) {
        return docService.getDoc(new Doc(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/fulltext/{term}")
    public Iterable<Doc> findByFullText(@PathVariable String term) {
        return docService.findByFullText(term);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public Doc update(@RequestBody Doc document) {
        return docService.updateDoc(document);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        docService.deleteDoc(new Doc(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Doc> getAll() {
        return docService.getAllDocs();
    }
}
