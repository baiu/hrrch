package com.baiu.hrrch.groupcatalog;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.baiu.hrrch.catalog.Catalog;
import com.baiu.hrrch.group.Group;

import java.util.List;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping("/api/group-catalogs")
public class GroupCatalogController {
    private final GroupCatalogService groupCatalogService;
    private final GroupCatalogMapper mapper;

    public GroupCatalogController(GroupCatalogService groupCatalogService, GroupCatalogMapper mapper) {
        this.groupCatalogService = groupCatalogService;
        this.mapper = mapper;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public GroupCatalogDto create(@RequestBody GroupCatalog groupCatalog) {
        return mapper.map(groupCatalogService.create(groupCatalog), GroupCatalogDto.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{groupCatalogId}")
    public GroupCatalogDto getById(@PathVariable Long groupCatalogId) {
        return mapper.map(groupCatalogService.get(new GroupCatalog(groupCatalogId)), GroupCatalogDto.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public GroupCatalogDto update(@RequestBody GroupCatalog groupCatalog) {
        return mapper.map(groupCatalogService.update(groupCatalog), GroupCatalogDto.class);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{groupCatalogId}")
    public void delete(@PathVariable Long groupCatalogId) {
        groupCatalogService.delete(new GroupCatalog(groupCatalogId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<GroupCatalogDto> getAll() {
        return mapper.mapAsList(groupCatalogService.getAll(), GroupCatalogDto.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{groupId}/{catalogId}")
    public GroupCatalogDto getByGroupAndCatalog(@PathVariable Long groupId, @PathVariable Long catalogId) {
        return mapper.map(groupCatalogService.getByGroupAndCatalog(new Group(groupId), new Catalog(catalogId)), GroupCatalogDto.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{groupId}")
    public List<GroupCatalogDto> getByGroupId(@PathVariable Long groupId) {
        return mapper.mapAsList(groupCatalogService.getByGroup(new Group(groupId)), GroupCatalogDto.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{catalogId}")
    public List<GroupCatalogDto> getByCatalogId(@PathVariable Long catalogId) {
        return mapper.mapAsList(groupCatalogService.getByCatalog(new Catalog(catalogId)), GroupCatalogDto.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/catalog-with-descendants")
    public List<GroupCatalogDto> createForCatalogWithDescendants(@RequestBody GroupCatalogDto groupCatalogDto) {
        return mapper.mapAsList(groupCatalogService.createForCatalogWithDescendants(mapper.map(groupCatalogDto, GroupCatalog.class)), GroupCatalogDto.class);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/catalog-with-descendants")
    public void deleteForCatalogWithDescendants(@RequestBody GroupCatalogDto groupCatalogDto) {
        groupCatalogService.deleteForCatalogWithDescendants(mapper.map(groupCatalogDto, GroupCatalog.class));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/can-edit-catalog-with-descendants")
    public int canEditForCatalogWithDescendants(@RequestBody GroupCatalogDto groupCatalogDto) {
        GroupCatalog groupCatalog = mapper.map(groupCatalogDto, GroupCatalog.class);
        return groupCatalogService.canEditForCatalogWithDescendants(groupCatalog, groupCatalog.isCanEdit());
    }
}
