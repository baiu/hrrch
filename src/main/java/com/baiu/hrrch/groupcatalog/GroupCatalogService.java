package com.baiu.hrrch.groupcatalog;

import com.baiu.hrrch.catalog.Catalog;
import com.baiu.hrrch.group.Group;

import java.util.List;

public interface GroupCatalogService {
    GroupCatalog create(GroupCatalog groupCatalog);

    GroupCatalog get(GroupCatalog groupCatalog);

    GroupCatalog update(GroupCatalog groupCatalog);

    void delete(GroupCatalog groupCatalog);

    List<GroupCatalog> getAll();

    GroupCatalog getByGroupAndCatalog(Group group, Catalog catalog);

    List<GroupCatalog> getByGroup(Group group);

    List<GroupCatalog> getByCatalog(Catalog catalog);

    /**
     * Добавить связь группа-каталог
     * для каталога и всех его потомков
     *
     * @param groupCatalog
     * @return groupCatalog
     */
    List<GroupCatalog> createForCatalogWithDescendants(GroupCatalog groupCatalog);

    /**
     * Удалить связь группа-каталог
     * для каталога и всех его потомков
     *
     * @param groupCatalog
     */
    void deleteForCatalogWithDescendants(GroupCatalog groupCatalog);

    /**
     * Изменить права редактирования в группа-каталог
     * для каталога и всех его потомков
     *
     * @param groupCatalog
     * @return количество измененных элементов
     */
    int canEditForCatalogWithDescendants(GroupCatalog groupCatalog, boolean canEdit);
}
