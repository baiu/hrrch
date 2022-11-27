package com.baiu.hrrch.doc;

import com.baiu.hrrch.catalog.Catalog;
import com.baiu.hrrch.person.Person;

import java.util.Collection;
import java.util.List;

public interface DocSimpleService {
    DocSimple createDoc(DocSimple doc);

    DocSimple getDoc(DocSimple doc);

    DocSimple updateDoc(DocSimple doc);

    void deleteDoc(DocSimple doc);

    /**
     * Получить документы из каталога доступные для пользователя
     *
     * @param person  Пользовать, вызвавший операцию
     * @param catalog Каталог
     * @return Коллекция доступных документов
     */
    List<DocSimple> getVisibleForPersonByCatalog(Person person, Catalog catalog);

    /**
     * Получить документы из коллекции каталогов
     *
     * @param person      Пользовать, вызвавший операцию
     * @param catalogList Коллекция каталогов
     * @return Коллекция документов
     */
    Collection<DocSimple> getByCatalogs(Person person, List<Catalog> catalogList);

    /**
     * Документ доступен для редактирования
     *
     * @param person Пользовать, вызвавший операцию
     * @param doc    Домумент
     * @return Доступен для редактирования
     */
    boolean canEdit(Person person, DocSimple doc);
}
