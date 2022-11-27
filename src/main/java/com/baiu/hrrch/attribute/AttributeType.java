package com.baiu.hrrch.attribute;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.baiu.hrrch.config.HistoryEntity;
import com.baiu.hrrch.person.Person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity(name = "attribute_type")
public class AttributeType implements HistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String guid;
    private LocalDateTime createDate;
    @ManyToOne
    @JsonIgnore
    private Person createPerson;
    private boolean actual;
    private String version;
    private Long weight;

    /**
     * Наименование атрибута
     */
    private String name;

    /**
     * Тип значения
     */
    private ValueType type;

    /**
     * Уникальность атрибута
     */
    private boolean uniqueAttribute;

    public AttributeType() {

    }

    public AttributeType(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUniqueAttribute() {
        return uniqueAttribute;
    }

    public void setUniqueAttribute(boolean unique) {
        this.uniqueAttribute = unique;
    }

    public ValueType getType() {
        return type;
    }

    public void setType(ValueType type) {
        this.type = type;
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

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean active) {
        this.actual = active;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    @Override
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public enum ValueType {
        BOOLEAN,
        NUMBER,
        STRING,
        LIST
    }
}
