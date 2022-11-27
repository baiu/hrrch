package com.baiu.hrrch.doc;

import org.springframework.data.jpa.repository.JpaRepository;
import com.baiu.hrrch.catalog.Catalog;

import java.util.List;
import java.util.UUID;

public interface DocSimpleRepository extends JpaRepository<DocSimple, Long> {
    DocSimple findDocSimpleByGlobalId(UUID globalId);

    List<DocSimple> findByParentCatalogs(Catalog catalog);

    List<DocSimple> findByAncestorCatalogsIn(List<Catalog> catalogs);

    List<DocSimple> findByParentCatalogsIn(List<Catalog> catalogs);
}
