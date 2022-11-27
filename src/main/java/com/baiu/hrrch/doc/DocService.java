package com.baiu.hrrch.doc;

import com.baiu.hrrch.catalog.Catalog;
import com.baiu.hrrch.person.Person;

import java.util.Collection;
import java.util.List;

public interface DocService {
    Doc createDoc(Doc doc);

    Doc getDoc(Doc doc);

    Doc updateDoc(Doc doc);

    void deleteDoc(Doc doc);

    List<Doc> getAllDocs();

    /**
     * Получить документы из каталога доступные для пользователя
     *
     * @param person  Пользовать, вызвавший операцию
     * @param catalog Каталог
     * @return Коллекция доступных документов
     */
    List<Doc> getVisibleForPersonByCatalog(Person person, Catalog catalog);

    /**
     * Получить документы из коллекции каталогов
     *
     * @param person      Пользовать, вызвавший операцию
     * @param catalogList Коллекция каталогов
     * @return Коллекция документов
     */
    Collection<Doc> getByCatalogs(Person person, List<Catalog> catalogList);

    /**
     * Документ доступен для редактирования
     *
     * @param person Пользовать, вызвавший операцию
     * @param doc    Домумент
     * @return Доступен для редактирования
     */
    boolean canEdit(Person person, Doc doc);

    /**
     * Полнотекстовый поиск по документам
     *
     * @param term Поисковый запрос
     * @return Страница документов
     */
    Iterable<Doc> findByFullText(String term);
}
