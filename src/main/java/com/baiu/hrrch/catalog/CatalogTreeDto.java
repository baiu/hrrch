package com.baiu.hrrch.catalog;

import java.util.List;
import java.util.Map;

public class CatalogTreeDto {
    private String id;
    private String title;
    private List<String> path;
    private CatalogTreeDto parent;
    private Map<String, CatalogTreeDto> children;

    public Map<String, CatalogTreeDto> getChildren() {
        return children;
    }

    public void setChildren(Map<String, CatalogTreeDto> children) {
        this.children = children;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public CatalogTreeDto getParent() {
        return parent;
    }

    public void setParent(CatalogTreeDto parent) {
        this.parent = parent;
    }
}
