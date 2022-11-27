package com.baiu.hrrch.catalog;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class CatalogDto {
    private Long id;
    private String name;
    private int idx;
    private Long parentId;
    @JsonIgnore
    private CatalogDto parent;
    private List<CatalogDto> children = new ArrayList<>();
    private String ancestorsPath;
    private int childCount;

    public CatalogDto() {

    }

    public CatalogDto(Catalog catalog) {
        this.id = catalog.getId();
        this.name = catalog.getName();
        this.idx = catalog.getIdx();
        this.parentId = catalog.getParentCatalog() != null ? catalog.getParentCatalog().getId() : null;
        this.ancestorsPath = catalog.getAncestorsPath();
        this.childCount = catalog.getChildCount();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public List<CatalogDto> getChildren() {
        return children;
    }

    public void setChildren(List<CatalogDto> children) {
        this.children = children;
    }

    public String getAncestorsPath() {
        return ancestorsPath;
    }

    public void setAncestorsPath(String ancestorsPath) {
        this.ancestorsPath = ancestorsPath;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public CatalogDto getParent() {
        return parent;
    }

    public void setParent(CatalogDto parent) {
        this.parent = parent;
    }
}
