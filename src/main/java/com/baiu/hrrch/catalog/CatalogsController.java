package com.baiu.hrrch.catalog;

import com.baiu.hrrch.doc.Doc;
import com.baiu.hrrch.doc.DocService;
import com.baiu.hrrch.doc.DocSimple;
import com.baiu.hrrch.doc.DocSimpleService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.baiu.hrrch.person.Person;
import com.baiu.hrrch.person.PersonService;

import java.util.List;

@RestController
@Secured("ROLE_USER")
@RequestMapping("/api/catalogs")
public class CatalogsController {
    private final CatalogService catalogService;
    private final PersonService personService;
    private final DocService docService;
    private final DocSimpleService docSimpleService;
    private final CatalogMapper catalogMapper;

    public CatalogsController(CatalogService catalogService, PersonService personService, DocService docService, DocSimpleService docSimpleService, CatalogMapper catalogMapper) {
        this.catalogService = catalogService;
        this.personService = personService;
        this.docService = docService;
        this.docSimpleService = docSimpleService;
        this.catalogMapper = catalogMapper;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CatalogDto2 create(@RequestBody CatalogDto2 catalog) {
        return catalogMapper.map(catalogService.createCatalog(catalogMapper.map(catalog, Catalog.class)), CatalogDto2.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{catalogId}")
    public CatalogDto2 getById(@PathVariable(required = true) Long catalogId) {
        return catalogMapper.map(catalogService.getCatalog(new Catalog(catalogId)), CatalogDto2.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public Catalog update(@RequestBody Catalog catalog) {
        return catalogService.updateCatalog(catalog);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{catalogId}")
    public void delete(@PathVariable Long catalogId) {
        catalogService.deleteCatalog(new Catalog(catalogId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public CatalogDto getAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = personService.getByLogin(auth.getName());
        return catalogService.getAllVisibleForPerson(person);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public CatalogData getAllData() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = personService.getByLogin(auth.getName());
        return new CatalogData(catalogService.getAllVisibleForPerson(person));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{catalogId}/children")
    public List<CatalogDto2> getChildren(@PathVariable Long catalogId) {
        return catalogMapper.mapAsList(catalogService.getChildren(new Catalog(catalogId)), CatalogDto2.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/descendants/{catalogId}")
    public List<CatalogDto2> getAllDescendants(@PathVariable Long catalogId) {
        return catalogMapper.mapAsList(catalogService.getAllDescendants(new Catalog(catalogId)), CatalogDto2.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{catalogId}/docs")
    public List<Doc> getDocsByCatalog(@PathVariable Long catalogId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = personService.getByLogin(auth.getName());
        return docService.getVisibleForPersonByCatalog(person, new Catalog(catalogId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{catalogId}/simple")
    public List<DocSimple> getSimpleByCatalog(@PathVariable Long catalogId) {
        return docSimpleService.getVisibleForPersonByCatalog(null, new Catalog(catalogId));
    }
}
