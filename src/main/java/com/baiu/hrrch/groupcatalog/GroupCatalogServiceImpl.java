package com.baiu.hrrch.groupcatalog;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baiu.hrrch.catalog.Catalog;
import com.baiu.hrrch.catalog.CatalogService;
import com.baiu.hrrch.exception.CustomInternalServerException;
import com.baiu.hrrch.exception.EntityNotFoundException;
import com.baiu.hrrch.group.Group;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupCatalogServiceImpl implements GroupCatalogService {
    private final GroupCatalogRepository groupCatalogRepository;
    private final CatalogService catalogService;

    public GroupCatalogServiceImpl(GroupCatalogRepository groupCatalogRepository, CatalogService catalogService) {
        this.groupCatalogRepository = groupCatalogRepository;
        this.catalogService = catalogService;
    }

    @Override
    public GroupCatalog create(GroupCatalog groupCatalog) {
        checkDuplicates(groupCatalog);
        return groupCatalogRepository.save(groupCatalog);
    }

    private void checkDuplicates(GroupCatalog groupCatalog) {
        if (groupCatalogRepository.existsByGroupAndCatalog(groupCatalog.getGroup(), groupCatalog.getCatalog())) {
            throw new CustomInternalServerException("Каталог уже доступен группе");
        }
    }

    @Override
    public GroupCatalog get(GroupCatalog groupCatalog) {
        return groupCatalogRepository.findById(groupCatalog.getId())
                .orElseThrow(() -> new EntityNotFoundException(groupCatalog.getClass(), groupCatalog.getId()));
    }

    @Override
    public GroupCatalog update(GroupCatalog groupCatalog) {
        checkDuplicates(groupCatalog);
        return groupCatalogRepository.save(groupCatalog);
    }

    @Override
    public void delete(GroupCatalog groupCatalog) {
        groupCatalogRepository.delete(groupCatalog);
    }

    @Override
    public List<GroupCatalog> getAll() {
        return groupCatalogRepository.findAll();
    }

    @Override
    public GroupCatalog getByGroupAndCatalog(Group group, Catalog catalog) {
        return groupCatalogRepository.getByGroupAndCatalog(group, catalog);
    }

    @Override
    public List<GroupCatalog> getByGroup(Group group) {
        return groupCatalogRepository.getByGroup(group);
    }

    @Override
    public List<GroupCatalog> getByCatalog(Catalog catalog) {
        return groupCatalogRepository.getByCatalog(catalog);
    }

    @Override
    public List<GroupCatalog> createForCatalogWithDescendants(GroupCatalog groupCatalog) {
        List<Catalog> allDescendants = catalogService.getAllDescendants(groupCatalog.getCatalog());
        List<GroupCatalog> newGroupCatalogs = new ArrayList<>(allDescendants.size());
        Group group = groupCatalog.getGroup();
        boolean canEdit = groupCatalog.isCanEdit();

        allDescendants.stream()
                .map(catalog -> new GroupCatalog(group, catalog, canEdit))
                .forEachOrdered(newGroupCatalogs::add);

        return groupCatalogRepository.saveAll(newGroupCatalogs);
    }

    @Transactional
    @Override
    public void deleteForCatalogWithDescendants(GroupCatalog groupCatalog) {
        List<Catalog> allDescendants = catalogService.getAllDescendants(groupCatalog.getCatalog());
        groupCatalogRepository.deleteInBatch(groupCatalogRepository.getByGroupAndCatalogIn(groupCatalog.getGroup(), allDescendants));
    }

    @Override
    public int canEditForCatalogWithDescendants(GroupCatalog groupCatalog, boolean canEdit) {
        List<Catalog> catalogs = catalogService.getAllDescendants(groupCatalog.getCatalog());
        return groupCatalogRepository.setCanEditForGroupAndCatalogs(groupCatalog.getGroup(), catalogs, groupCatalog.isCanEdit());
    }
}
