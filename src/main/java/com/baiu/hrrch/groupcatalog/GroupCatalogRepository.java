package com.baiu.hrrch.groupcatalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.baiu.hrrch.catalog.Catalog;
import com.baiu.hrrch.group.Group;

import java.util.List;

public interface GroupCatalogRepository extends JpaRepository<GroupCatalog, Long> {
    @Modifying
    @Query("update group_catalog gc set gc.canEdit = :canEdit where gc.group = :group and gc.catalog in (:catalogs)")
    int setCanEditForGroupAndCatalogs(@Param("group") Group group, @Param("catalogs") List<Catalog> catalogs, @Param("canEdit") boolean canEdit);

    List<GroupCatalog> getByGroupAndCatalogIn(Group group, List<Catalog> allDescendants);

    GroupCatalog getByGroupAndCatalog(Group group, Catalog catalog);

    boolean existsByGroupAndCatalog(Group group, Catalog catalog);

    List<GroupCatalog> getByGroup(Group group);

    List<GroupCatalog> getByCatalog(Catalog catalog);
}
