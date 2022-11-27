-- тип документа
INSERT INTO attribute_type (active, version, create_date, name, type, unique_attribute, create_person_id, weight)
VALUES (true, '1', now(), 'Тип документа', 3, false, 1, 0);
-- значения типов документа
INSERT INTO attribute_option (active, version, create_date, create_person_id, attribute_type_id, value, weight)
VALUES (true, '1', now(), 1, 1, 'Управленческие документы', 0);
INSERT INTO attribute_option (active, version, create_date, create_person_id, attribute_type_id, value, weight)
VALUES (true, '1', now(), 1, 1, 'Технические документы', 1);
INSERT INTO attribute_option (active, version, create_date, create_person_id, attribute_type_id, value, weight)
VALUES (true, '1', now(), 1, 1, 'Приказ', 2);
INSERT INTO attribute_option (active, version, create_date, create_person_id, attribute_type_id, value, weight)
VALUES (true, '1', now(), 1, 1, 'Инструкция', 3);
INSERT INTO attribute_option (active, version, create_date, create_person_id, attribute_type_id, value, weight)
VALUES (true, '1', now(), 1, 1, 'Договор', 4);
INSERT INTO attribute_option (active, version, create_date, create_person_id, attribute_type_id, value, weight)
VALUES (true, '1', now(), 1, 1, 'Чертеж', 5);
INSERT INTO attribute_option (active, version, create_date, create_person_id, attribute_type_id, value, weight)
VALUES (true, '1', now(), 1, 1, 'Паспорт', 6);
INSERT INTO attribute_option (active, version, create_date, create_person_id, attribute_type_id, value, weight)
VALUES (true, '1', now(), 1, 1, 'Отчет об экспертизе', 6);
INSERT INTO attribute_option (active, version, create_date, create_person_id, attribute_type_id, value, weight)
VALUES (true, '1', now(), 1, 1, 'Пояснительная записка', 8);
