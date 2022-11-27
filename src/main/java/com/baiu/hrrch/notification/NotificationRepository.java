package com.baiu.hrrch.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import com.baiu.hrrch.person.Person;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByPerson(Person person);

    boolean existsNotificationByPersonAndEventType(Person person, EventType eventType);
}
