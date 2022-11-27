package com.baiu.hrrch.catalog;

public class CatalogDto2 {
    private Long id;
    private String name;
    private int idx;
    private Long parentId;

    public CatalogDto2() {

    }

    public CatalogDto2(Catalog catalog) {
        this.id = catalog.getId();
        this.name = catalog.getName();
        this.idx = catalog.getIdx();
        this.parentId = catalog.getParentCatalog() != null ? catalog.getParentCatalog().getId() : null;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
