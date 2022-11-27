package com.baiu.hrrch.attribute;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;

public class AttributeValue {

    /**
     * Идентификатор атрибута
     */
    @Indexed
    private Long AttributeTypeId;

    /**
     * Наименование атрибута
     */
    @TextIndexed
    private String name;

    /**
     * Тип атрибута
     */
    private AttributeType.ValueType valueType;

    /**
     * Значение атрибута
     */
    @TextIndexed
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getAttributeTypeId() {
        return AttributeTypeId;
    }

    public void setAttributeTypeId(Long attributeTypeId) {
        AttributeTypeId = attributeTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AttributeType.ValueType getValueType() {
        return valueType;
    }

    public void setValueType(AttributeType.ValueType valueType) {
        this.valueType = valueType;
    }
}
