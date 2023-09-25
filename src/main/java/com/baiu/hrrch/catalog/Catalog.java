package com.baiu.hrrch.catalog;

import com.baiu.hrrch.attribute.AttributeType;
import com.baiu.hrrch.person.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Type;
import org.hibernate.proxy.HibernateProxy;

@Entity(name = "catalog")
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Person createPerson;
    private boolean active;
    private boolean personal;

    /**
     * Наименование каталога
     */
    private String name;

    /**
     * Наименование каталога в нижнем регистре
     */
    private String lname;

    /**
     * Индекс каталога для отображения в дереве
     */
    private int idx;

    /**
     * Родитель каталога
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Catalog parentCatalog;

    /**
     * Дети каталога
     */
    @OneToMany(mappedBy = "parentCatalog", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Catalog> childrenCatalogs;

    /**
     * Предки каталога в порядке от корня до родителя
     */
    @Column(columnDefinition = "ltree")
    @Type(type = "com.baiu.hrrch.catalog.LTreeType")
    private String ancestorsPath;

    /**
     * Атрибуты каталога
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "catalog_attribute_type",
            joinColumns = @JoinColumn(name = "catalog_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_type_id", referencedColumnName = "id")
    )
    private Set<AttributeType> attributeTypes = new HashSet<>();

//    /**
//     * Фасеты для поиска
//     */
//    @ManyToMany
//    private List<DocAttributeType> facets;

    /**
     * Количество детей
     */
    private int childCount;

    public Catalog() {
    }

    public Catalog(Long id) {
        setId(id);
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

    public Person getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(Person createPerson) {
        this.createPerson = createPerson;
    }

    @JsonIgnore
    public Catalog getParentCatalog() {
        return parentCatalog;
    }

    @JsonProperty
    public void setParentCatalog(Catalog parentCatalog) {
        this.parentCatalog = parentCatalog;
    }

    public Set<Catalog> getChildrenCatalogs() {
        return childrenCatalogs;
    }

    public void setChildrenCatalogs(Set<Catalog> childrenCatalogs) {
        this.childrenCatalogs = childrenCatalogs;
    }

    public Set<AttributeType> getAttributeTypes() {
        return attributeTypes;
    }

    public void setAttributeTypes(Set<AttributeType> attributeTypes) {
        this.attributeTypes = attributeTypes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public String getAncestorsPath() {
        return ancestorsPath;
    }

    public void setAncestorsPath(String ancestors) {
        this.ancestorsPath = ancestors;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isPersonal() {
        return personal;
    }

    public void setPersonal(boolean personal) {
        this.personal = personal;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) {
            return false;
        }
        Catalog catalog = (Catalog) o;
        return getId() != null && Objects.equals(getId(), catalog.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
