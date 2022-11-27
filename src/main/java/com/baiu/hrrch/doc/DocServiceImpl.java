package com.baiu.hrrch.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.baiu.hrrch.catalog.Catalog;
import com.baiu.hrrch.catalog.CatalogService;
import com.baiu.hrrch.exception.CatalogAccessDeniedException;
import com.baiu.hrrch.exception.EntityNotFoundException;
import com.baiu.hrrch.person.Person;

import java.util.Collection;
import java.util.List;

@Service
public class DocServiceImpl implements DocService {

    private final DocRepository docRepository;
    private final DocSimpleService docSimpleService;
    private final MongoTemplate mongoTemplate;
    private final CatalogService catalogService;

    @Autowired
    public DocServiceImpl(DocRepository docRepository, DocSimpleService docSimpleService, MongoTemplate mongoTemplate, CatalogService catalogService) {
        this.docRepository = docRepository;
        this.docSimpleService = docSimpleService;
        this.mongoTemplate = mongoTemplate;
        this.catalogService = catalogService;
    }

    @Override
    public Doc createDoc(Doc doc) {
        docSimpleService.createDoc(new DocSimple(doc));
        return docRepository.save(doc);
    }

    @Override
    public Doc getDoc(Doc doc) {
        return docRepository.findById(doc.getId())
                .orElseThrow(() -> new EntityNotFoundException(doc.getClass(), doc.getId()));
    }

    @Override
    public Doc updateDoc(Doc doc) {
        return docRepository.save(doc);
    }

    @Override
    public void deleteDoc(Doc doc) {
        doc.setActive(false);
        docRepository.save(doc);
    }

    @Override
    public List<Doc> getAllDocs() {
        return docRepository.findAll();
    }

    @Override
    public List<Doc> getVisibleForPersonByCatalog(Person person, Catalog catalog) {
        if (!canView(person, catalog)) {
            throw new CatalogAccessDeniedException(person, catalog);
        }
        return docRepository.findByParentCatalogsIds(catalog.getId());
    }

    private boolean canView(Person person, Catalog catalog) {
        return catalogService.canView(person, catalog);
    }

    @Override
    public Collection<Doc> getByCatalogs(Person person, List<Catalog> catalogList) {
        return docRepository.findByParentCatalogsIdsIn(catalogList);
    }

    @Override
    public boolean canEdit(Person person, Doc doc) {
        return false;
    }

    @Override
    public Iterable<Doc> findByFullText(String term) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // cписок доступных для поиска каталогов
        List<Long> catalogIds = catalogService.getAllCatalogByPersonLogin(auth.getName());
        Query query = TextQuery.queryText(TextCriteria
                .forDefaultLanguage()
                .caseSensitive(false)
                .matchingPhrase(term))
                .sortByScore();
        query.addCriteria(Criteria.where("active").is(true)
                .and("ancestorCatalogsIds").in(catalogIds));
        return mongoTemplate.find(query, Doc.class);
    }
}
