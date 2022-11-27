package com.baiu.hrrch.notification;

public enum EventType {
    /**
     * Истечение срока хранения
     * документов
     */
    DOC_DESTRUCTION_DATE,

    /**
     * Истечение срока обновления
     * документов
     */
    DOC_CYCLIC_DATE,

    /**
     * Добавлен комментарий для
     * документа
     */
    DOC_COMMENT,

    /**
     * Заказ создан
     */
    ORDER_CREATE,

    /**
     * Заказ исполнен
     */
    ORDER_FINISHED,

    /**
     * Заказ отклонен
     */
    ORDER_REJECTED,

    /**
     * Добавлен комментарий для
     * заказа
     */
    ORDER_COMMENT
}
