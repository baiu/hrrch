package com.baiu.hrrch.task;

import com.baiu.hrrch.doc.DocSimple;
import com.baiu.hrrch.person.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Задачи между пользователями и/или работниками архива.
 * Например на физические копии или оригиналы документов,
 * на подготовку пакетов документов для сторонних организаций.
 * Сюда же можно включить функционал по контролю на сдачу документов в архив.
 *
 * @see TaskService
 */
@Entity(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createDate;
    private boolean active;
    /**
     * Заказчик
     */
    @ManyToOne
    private Person createPerson;

    /**
     * Исполнитель
     */
    @ManyToOne
    private Person executorPerson;

    /**
     * Пакет документов
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "task_docs",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "doc_id")
    )
    @JsonIgnore
    private Set<DocSimple> docs;

    /**
     * Статус задачи
     */
    private TaskStatus taskStatus;

    /**
     * Процесс
     */
    @ManyToOne
    private Process process;

    public Task() {
    }

    public Task(Long taskId) {
        this.id = taskId;
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

    public Person getExecutorPerson() {
        return executorPerson;
    }

    public void setExecutorPerson(Person executorPerson) {
        this.executorPerson = executorPerson;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public Set<DocSimple> getDocs() {
        return docs;
    }

    public void setDocs(Set<DocSimple> docs) {
        this.docs = docs;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
