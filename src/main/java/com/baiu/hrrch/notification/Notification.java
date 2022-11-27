package com.baiu.hrrch.notification;

import com.baiu.hrrch.doc.DocSimple;
import com.baiu.hrrch.person.Person;
import com.baiu.hrrch.task.Task;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Тип события
     */
    private EventType eventType;

    /**
     * Пользователь
     */
    @ManyToOne
    private Person person;

    /**
     * Задача
     */
    @ManyToOne
    private Task task;

    /**
     * Документ
     */
    @ManyToOne
    private DocSimple doc;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public DocSimple getDoc() {
        return doc;
    }

    public void setDoc(DocSimple doc) {
        this.doc = doc;
    }
}
