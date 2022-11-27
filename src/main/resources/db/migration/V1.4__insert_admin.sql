insert into person (active, login, password)
values (true, 'admin', '$2a$10$77cAM.wlexGWLYlkJnmO7eIlD0gply2mVFfobFT23b3OcB3gSWGuW');

insert into role (name, description)
values ('ROLE_ADMIN', 'роль администратор');

insert into groups (description)
values ('группа администраторов');

insert into group_person (group_id, person_id)
values (1, 1);

insert into group_role (group_id, role_id)
values (1, 1);
