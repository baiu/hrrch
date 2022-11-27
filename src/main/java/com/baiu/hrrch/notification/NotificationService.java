package com.baiu.hrrch.notification;

import com.baiu.hrrch.person.Person;

import java.util.List;

public interface NotificationService {

    Notification createNotification(Person person, Notification notification);

    Notification getNotification(Notification notification);

    Notification updateNotification(Person person, Notification notification);

    void deleteNotification(Person person, Notification notification);

    /**
     * Поиск всех подписок пользователя
     *
     * @param person  Пользовать, вызвавший операцию
     * @param forWhom Пользовать, для кого ведется поиск
     * @return Список уведомлений пользовотеля
     */
    List<Notification> findAllSubscribed(Person person, Person forWhom);

    /**
     * Наличие уведомлений по типу и пользователю
     *
     * @param person  Пользовать для кого ведется поиск
     * @param eventType тип уведомлений
     * @return Возвращает true если существует
     */
    boolean isExistsByPersonAndType(Person person, EventType eventType);
}
