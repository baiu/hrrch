package com.baiu.hrrch.catalog;

import com.baiu.hrrch.attribute.AttributeType;
import com.baiu.hrrch.person.Person;

import java.util.Collection;
import java.util.List;

public interface CatalogService {

    Catalog createCatalog(Catalog catalog);

    Catalog getCatalog(Catalog catalog);

    Catalog updateCatalog(Catalog catalog);

    void deleteCatalog(Catalog catalog);

    List<Catalog> getAllCatalogs();

    /**
     * Получить список детей
     *
     * @param catalog каталог
     * @return список детей
     */
    List<Catalog> getChildren(Catalog catalog);

    /**
     * Получить всех потомков, включая внуков
     *
     * @param parentCatalog каталог
     * @return список всех потомков
     */
    List<Catalog> getAllDescendants(Catalog parentCatalog);

    /**
     * Получить каталоги с атрибутом
     *
     * @param person    Пользовать, вызвавший операцию
     * @param attribute атрибут документа
     * @return список каталогов
     */
    Collection<Catalog> getCatalogsByDocAttributes(Person person, AttributeType attribute);

    Catalog findByIdAndFetchAttributeTypes(Long catalogId);

    CatalogDto getCatalogDtoTreeFromList(List<Catalog> catalogs);

    CatalogDto getAllVisibleForPerson(Person person);

    List<CatalogDto> getAllCatalogsByPerson(Person person);

    List<Long> getAllCatalogByPersonLogin(String login);

    boolean canView(Person person, Catalog catalog);
}
