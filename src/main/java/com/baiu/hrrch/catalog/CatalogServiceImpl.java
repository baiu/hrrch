package com.baiu.hrrch.catalog;

import com.baiu.hrrch.attribute.AttributeType;
import com.baiu.hrrch.exception.CatalogAccessDeniedException;
import com.baiu.hrrch.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.baiu.hrrch.person.Person;
import com.baiu.hrrch.person.PersonService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;
    private final CatalogMapper catalogMapper;
    private final PersonService personService;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository, CatalogMapper catalogMapper, PersonService personService) {
        this.catalogRepository = catalogRepository;
        this.catalogMapper = catalogMapper;
        this.personService = personService;
    }

    @Override
    public Catalog createCatalog(Catalog catalog) {
        return catalogRepository.save(catalog);
    }

    @Override
    public Catalog getCatalog(Catalog catalog) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = personService.getByLogin(auth.getName());
        checkCanView(person, catalog);
        return catalogRepository.findById(catalog.getId())
                .orElseThrow(() -> new EntityNotFoundException(catalog.getClass(), catalog.getId()));
    }

    private void checkCanView(Person person, Catalog catalog) {
        if (!canView(person, catalog)) {
            throw new CatalogAccessDeniedException();
        }
    }

    @Override
    public Catalog updateCatalog(Catalog catalog) {
        return catalogRepository.save(catalog);
    }

    @Override
    public void deleteCatalog(Catalog catalog) {
        catalog.setActive(false);
        catalogRepository.save(catalog);
    }

    @Override
    public List<Catalog> getAllCatalogs() {
        Authentication auth = getContext().getAuthentication();
        boolean admin = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch("ROLE_ADMIN"::equals);
        if (admin) {
            return catalogRepository.findAll();
        }
        Person person = personService.getByLogin(auth.getName());
        return catalogRepository.getAllCatalogsForPerson(person);
    }

    @Override
    public List<Catalog> getChildren(Catalog catalog) {
        return catalogRepository.getCatalogsByParentCatalog(catalog);
    }

    @Override
    public List<Catalog> getAllDescendants(Catalog parentCatalog) {
        return catalogRepository.getAllDescendants(parentCatalog.getId());
    }

    @Override
    public Collection<Catalog> getCatalogsByDocAttributes(Person person, AttributeType attribute) {
        return catalogRepository.getCatalogsByAttributeTypes(attribute);
    }

    @Override
    public Catalog findByIdAndFetchAttributeTypes(Long catalogId) {
        return catalogRepository.findByIdAndFetchAttributeTypes(catalogId);
    }

    @Override
    public CatalogDto getCatalogDtoTreeFromList(List<Catalog> catalogs) {
        Map<Long, CatalogDto> cats = new HashMap<>();
        catalogs.forEach(catalog -> cats.put(catalog.getId(), new CatalogDto(catalog)));
        cats.values().forEach(catalogDto ->
                {
                    CatalogDto proposedParent;
                    if (cats.containsKey(catalogDto.getParentId())) {
                        proposedParent = cats.get(catalogDto.getParentId());
                        catalogDto.setParent(proposedParent);
                        proposedParent.getChildren().add(catalogDto);
                    }
                }
        );
        return cats.values().stream().filter(x -> x.getParent() == null).findFirst().orElse(null);
    }

    @Override
    public CatalogDto getAllVisibleForPerson(Person person) {
        List<Catalog> allVisibleByPerson = catalogRepository.getAllCatalogsForPerson(person);
        return getCatalogDtoTreeFromList(allVisibleByPerson);
    }

    @Override
    public List<CatalogDto> getAllCatalogsByPerson(Person person) {
        return catalogMapper.mapAsList(catalogRepository.getAllCatalogsForPerson(person), CatalogDto.class);
    }

    @Override
    public List<Long> getAllCatalogByPersonLogin(String login) {
        return catalogRepository.getAllCatalogsByPersonLogin(login);
    }

    @Override
    public boolean canView(Person person, Catalog catalog) {
        return catalogRepository.existsVisibleCatalog(person, catalog);
    }
}
