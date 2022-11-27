package com.baiu.hrrch.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baiu.hrrch.exception.EntityNotFoundException;
import com.baiu.hrrch.person.Person;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification createNotification(Person person, Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification getNotification(Notification notification) {
        return notificationRepository.findById(notification.getId())
                .orElseThrow(() -> new EntityNotFoundException(notification.getClass(), notification.getId()));
    }

    @Override
    public Notification updateNotification(Person person, Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void deleteNotification(Person person, Notification notification) {
        notificationRepository.delete(notification);
    }

    @Override
    public List<Notification> findAllSubscribed(Person person, Person forWhom) {
        return notificationRepository.findAllByPerson(forWhom);
    }

    @Override
    public boolean isExistsByPersonAndType(Person person, EventType eventType) {
        return notificationRepository.existsNotificationByPersonAndEventType(person, eventType);
    }
}
