package com.baiu.hrrch.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baiu.hrrch.catalog.Catalog;
import com.baiu.hrrch.exception.EntityNotFoundException;
import com.baiu.hrrch.person.Person;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class DocSimpleServiceImpl implements DocSimpleService {

    private final DocSimpleRepository docSimpleRepository;

    @Autowired
    public DocSimpleServiceImpl(DocSimpleRepository docSimpleRepository) {
        this.docSimpleRepository = docSimpleRepository;
    }

    @Override
    public DocSimple createDoc(DocSimple doc) {
        return docSimpleRepository.save(doc);
    }

    @Override
    public DocSimple getDoc(DocSimple doc) {
        return docSimpleRepository.findById(doc.getId())
                .orElseThrow(() -> new EntityNotFoundException(doc.getClass(), doc.getId()));
    }

    @Override
    public DocSimple updateDoc(DocSimple doc) {
        return docSimpleRepository.save(doc);
    }

    @Override
    public void deleteDoc(DocSimple doc) {
        doc.setActive(false);
        docSimpleRepository.save(doc);
    }

    @Override
    public List<DocSimple> getVisibleForPersonByCatalog(Person person, Catalog catalog) {
//        if (catalogNotVisibleForPerson(person, catalog)) {
//            throw new NotAuthorizedException();
//        }
        return docSimpleRepository.findByParentCatalogs(catalog);
    }

    public DocSimple findDocSimpleByGlobalId(UUID globalId) {
        return docSimpleRepository.findDocSimpleByGlobalId(globalId);
    }

    private boolean catalogNotVisibleForPerson(Person person, Catalog catalog) {
//        return disjoint(person.getPersonCatalogs().stream(), catalog.getAncestorsPath().split("."));
        return false;
    }

    @Override
    public Collection<DocSimple> getByCatalogs(Person person, List<Catalog> catalogList) {
        return docSimpleRepository.findByParentCatalogsIn(catalogList);
    }

    @Override
    public boolean canEdit(Person person, DocSimple doc) {
        return false;
    }
}
