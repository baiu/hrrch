package com.baiu.hrrch.attribute;

import com.baiu.hrrch.doc.Doc;
import com.baiu.hrrch.catalog.Catalog;
import com.baiu.hrrch.person.Person;

import java.util.Collection;
import java.util.List;

public interface AttributeTypeService {

    AttributeType createAttributeType(AttributeType attributeType);

    AttributeType getAttributeType(AttributeType attributeType);

    AttributeType updateAttributeType(AttributeType attributeType);

    void deleteAttributeType(AttributeType attributeType);

    Collection<AttributeType> getAllDocAttribute(Person person);

    /**
     * Получить список атрибутов для документа
     *
     * @param doc документ
     * @return список атрибутов
     */
    Collection<AttributeType> getAllByDoc(Doc doc);

    /**
     * Получить список атрибутов для каталога
     *
     * @param catalog каталог
     * @return список атрибутов
     */
    Collection<AttributeType> getByCategory(Catalog catalog);

    /**
     * Существует атрибут у документа
     *
     * @param doc документ
     * @return атрибут существует
     */
    boolean attributeExist(Doc doc);

    AttributeOption createAttributeOption(AttributeType attributeType, String option);

    List<AttributeType> getAllAttributeTypes();
}
