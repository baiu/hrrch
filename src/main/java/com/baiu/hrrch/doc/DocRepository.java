package com.baiu.hrrch.doc;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.baiu.hrrch.catalog.Catalog;

import java.util.List;
import java.util.UUID;

public interface DocRepository extends MongoRepository<Doc, String> {
    List<Doc> findByParentCatalogsIds(Long catalogId);

    List<Doc> findByAncestorCatalogsIdsIn(List<Catalog> catalogs);

    List<Doc> findByParentCatalogsIdsIn(List<Catalog> catalogs);

    Doc findByGlobalId(UUID globalId);
}
