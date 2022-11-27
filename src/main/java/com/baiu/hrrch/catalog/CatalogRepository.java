package com.baiu.hrrch.catalog;

import com.baiu.hrrch.attribute.AttributeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.baiu.hrrch.person.Person;

import java.util.List;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Long> {

    @Query(value = "SELECT * " +
            " FROM catalog c " +
            " WHERE c.ancestors_path <@ ( " +
            "               SELECT c2.ancestors_path " +
            "               FROM catalog c2 " +
            "               WHERE c2.id = :catalogId)", nativeQuery = true)
    List<Catalog> getAllDescendants(@Param("catalogId") Long catalogId);

    @Query("SELECT c FROM catalog c WHERE c.parentCatalog = :parentCatalog")
    List<Catalog> getCatalogsByParentCatalog(@Param("parentCatalog") Catalog parentCatalog);

    List<Catalog> getCatalogsByAttributeTypes(AttributeType attribute);

    @Query("SELECT c FROM catalog c LEFT JOIN FETCH c.attributeTypes WHERE c.id = (:catalogId)")
    Catalog findByIdAndFetchAttributeTypes(@Param("catalogId") Long catalogId);

    @Query("select distinct c " +
            "from groups g " +
            "join g.groupCatalogs gc " +
            "join gc.catalog c " +
            "join g.persons p " +
            "where p = :person")
    List<Catalog> getAllCatalogsForPerson(@Param("person") Person person);

    @Query("select distinct c.id " +
            "from groups g " +
            "join g.groupCatalogs gc " +
            "join gc.catalog c " +
            "join g.persons p " +
            "where p.login = :login")
    List<Long> getAllCatalogsByPersonLogin(@Param("login") String login);

    @Query("select (count(c) > 0) " +
            "from groups g " +
            "join g.groupCatalogs gc " +
            "join gc.catalog c " +
            "join g.persons p " +
            "where p = :person and c = :catalog")
    boolean existsVisibleCatalog(@Param("person") Person person, @Param("catalog") Catalog catalog);
}
