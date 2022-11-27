package com.baiu.hrrch.doc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.baiu.hrrch.catalog.Catalog;
import com.baiu.hrrch.person.Person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "doc_simple")
public class DocSimple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createDate;
    @ManyToOne
    private Person createPerson;
    private boolean active;
    private int version;

    /**
     * Глобальный идентификатор документа
     * для альфреско и монгодб
     */
    private UUID globalId;

    /**
     * Наименование документа
     */
    private String name;

    /**
     * Список каталогов в которых состоит документ
     */
    @OneToMany()
    @JoinTable(name = "doc_simple_parent_catalogs",
            joinColumns = @JoinColumn(name = "doc_simple_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_catalog_id")
    )
    @JsonIgnore
    private Set<Catalog> parentCatalogs = new HashSet<>();

    /**
     * Множество каталогов включая предков в которых состоит документ
     */
    @OneToMany
    @JoinTable(name = "doc_simple_ancestor_catalogs",
            joinColumns = @JoinColumn(name = "doc_simple_id"),
            inverseJoinColumns = @JoinColumn(name = "ancestor_catalog_id")
    )
    @JsonIgnore
    private Set<Catalog> ancestorCatalogs = new HashSet<>();

    public DocSimple() {
    }

    public DocSimple(Doc doc) {
        createDate = doc.getCreateDate();
        active = doc.isActive();
        version = doc.getVersion();
        globalId = doc.getGlobalId();
        name = doc.getName();
    }

    public UUID getGlobalId() {
        return globalId;
    }

    public void setGlobalId(UUID globalId) {
        this.globalId = globalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Set<Catalog> getParentCatalogs() {
        return parentCatalogs;
    }

    public void setParentCatalogs(Set<Catalog> parentCatalogsIds) {
        this.parentCatalogs = parentCatalogsIds;
    }

    public Set<Catalog> getAncestorCatalogs() {
        return ancestorCatalogs;
    }

    public void setAncestorCatalogs(Set<Catalog> ancestorsOfCatalogsIds) {
        this.ancestorCatalogs = ancestorsOfCatalogsIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCreatePerson(Person createPerson) {
        this.createPerson = createPerson;
    }
}
