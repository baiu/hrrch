package com.baiu.hrrch.groupcatalog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import com.baiu.hrrch.catalog.Catalog;
import com.baiu.hrrch.group.Group;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "group_catalog")
@EqualsAndHashCode
public class GroupCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    private Group group;
    @ManyToOne
    private Catalog catalog;
    private boolean canEdit;

    public GroupCatalog() {

    }

    public GroupCatalog(Long id) {
        this.id = id;
    }

    public GroupCatalog(Group group, Catalog catalog, boolean canEdit) {
        this.group = group;
        this.catalog = catalog;
        this.canEdit = canEdit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }
}
