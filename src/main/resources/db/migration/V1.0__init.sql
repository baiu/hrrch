create table person
(
    id       bigserial primary key,
    active   boolean not null,
    login    varchar(255),
    password varchar(255)
);

create table attribute_type
(
    id               bigserial primary key,
    active           boolean not null,
    version          varchar(255),
    create_date      timestamp,
    name             varchar(255),
    type             integer,
    unique_attribute boolean not null,
    create_person_id bigint,
    weight           bigint,

    constraint attribute_type_person_fk
        foreign key (create_person_id) references person
);

create table attribute_option
(
    id                bigserial primary key,
    active            boolean not null,
    version           varchar(255),
    create_date       timestamp,
    create_person_id  bigint,
    attribute_type_id bigint  not null,
    value             varchar(255),
    weight            bigint,

    constraint attribute_option_person_fk
        foreign key (create_person_id) references person,
    constraint attribute_option_attribute_type_fk
        foreign key (attribute_type_id) references attribute_type
);

create table catalog
(
    id                bigserial primary key,
    active            boolean                     not null,
    child_count       integer                     not null,
    create_date       timestamp without time zone not null default now(),
    idx               integer                     not null,
    lname             varchar(255),
    name              varchar(255)                not null,
    create_person_id  bigint,
    parent_catalog_id bigint,
    ancestors_path    ltree,

    constraint catalog_person_fk foreign key (create_person_id) references person (id),
    constraint parent_catalog_fk foreign key (parent_catalog_id) references catalog (id)
);

create table catalog_attribute_type
(
    id                bigserial primary key,
    catalog_id        bigint not null,
    attribute_type_id bigint not null,

    constraint catalog_attribute_type_dat_fk
        foreign key (attribute_type_id) references attribute_type,
    constraint catalog_attribute_type_catalog_fk
        foreign key (catalog_id) references catalog,
    constraint catalog_attribute_type_unique
        unique (catalog_id, attribute_type_id)
);

create table department
(
    id   bigserial primary key,
    name varchar(255)
);

create table contact
(
    id               bigserial primary key,
    business_phone   varchar(255),
    cellular_phone   varchar(255),
    create_person_id bigint,
    create_date      timestamp,
    description      varchar(255),
    email            varchar(255),
    name             varchar(255),
    name_for_contact varchar(255),
    patronymic       varchar(255),
    position         varchar(255),
    surname          varchar(255),
    department_id    bigint,
    person_id        bigint,

    constraint contact_person_fk
        foreign key (person_id) references contact,
    constraint contact_department_fk
        foreign key (department_id) references department,
    constraint contact_create_person_fk
        foreign key (create_person_id) references person
);

create table dictionary
(
    id               bigserial primary key,
    active           boolean not null,
    create_date      timestamp,
    create_person_id bigint,
    description      varchar(255),
    name             varchar(255),

    constraint dictionary_create_person_fk
        foreign key (create_person_id) references person
);

create table dictionary_data
(
    id         bigserial primary key,
    as_default boolean,
    value      varchar(255),
    dic_id     bigint,

    constraint dictionary_data_dictionary_fk
        foreign key (dic_id) references dictionary
);

create table doc_simple
(
    id               bigserial primary key,
    active           boolean not null,
    create_date      timestamp,
    global_id        uuid,
    name             varchar(255),
    version          integer not null,
    create_person_id bigint,

    constraint doc_simple_person_fk
        foreign key (create_person_id) references person
);

create table doc_simple_ancestor_catalogs
(
    doc_simple_id       bigint not null,
    ancestor_catalog_id bigint not null,

    constraint doc_simple_ancestor_catalogs_catalog_fk
        foreign key (ancestor_catalog_id) references catalog,
    constraint doc_simple_ancestor_catalogs_doc_simple_fk
        foreign key (doc_simple_id) references doc_simple,
    constraint doc_simple_ancestor_catalogs_unique
        unique (ancestor_catalog_id, doc_simple_id)
);

create table doc_simple_parent_catalogs
(
    doc_simple_id     bigint not null,
    parent_catalog_id bigint not null,

    constraint doc_simple_parent_catalogs_catalog_fk
        foreign key (parent_catalog_id) references catalog,
    constraint doc_simple_parent_catalogs_doc_simple_fk
        foreign key (doc_simple_id) references doc_simple,
    constraint doc_simple_parent_catalogs_unique
        unique (parent_catalog_id, doc_simple_id)
);

create table groups
(
    id          bigserial primary key,
    description varchar(512)
);

create table group_catalog
(
    id         bigserial primary key,
    group_id   bigint not null,
    catalog_id bigint not null,

    constraint group_catalog_catalog_fk
        foreign key (catalog_id) references catalog,
    constraint group_catalog_group_fk
        foreign key (group_id) references groups,
    constraint group_catalog_unique
        unique (group_id, catalog_id)
);

create table group_person
(
    id        bigserial primary key,
    group_id  bigint not null,
    person_id bigint not null,

    constraint group_person_person_fk
        foreign key (person_id) references person,
    constraint group_person_group_fk
        foreign key (group_id) references groups,
    constraint group_person_unique
        unique (group_id, person_id)
);

create table role
(
    id               bigserial primary key,
    create_date      timestamp,
    name             varchar(255),
    description      varchar(255),
    create_person_id bigint,

    constraint role_create_person_fk
        foreign key (create_person_id) references person
);

create table group_role
(
    id       bigserial primary key,
    group_id bigint not null,
    role_id  bigint not null,

    constraint group_role_role_fk
        foreign key (role_id) references role,
    constraint group_role_group_fk
        foreign key (group_id) references groups,
    constraint group_role_unique
        unique (group_id, role_id)
);

create table process
(
    id               bigserial primary key,
    active           boolean not null,
    create_date      timestamp,
    end_date         timestamp,
    create_person_id bigint
);

create table task
(
    id                 bigserial primary key,
    active             boolean not null,
    create_date        timestamp,
    create_person_id   bigint,
    task_status        integer,
    process_id         bigint,
    executor_person_id bigint,

    constraint task_process_fk
        foreign key (process_id) references process,
    constraint task_create_person_fk
        foreign key (create_person_id) references person,
    constraint task_executor_person_fk
        foreign key (executor_person_id) references person
);

create table task_docs
(
    id      bigserial primary key,
    task_id bigint not null,
    doc_id  bigint not null,

    constraint task_docs_doc_simple_fk
        foreign key (doc_id) references doc_simple,
    constraint task_docs_task_fk
        foreign key (task_id) references task,
    constraint task_docs_unique
        unique (doc_id, task_id)
);

create table notification
(
    id         bigserial primary key,
    event_type integer,
    person_id  bigint,
    doc_id     bigint,
    task_id    bigint,

    constraint notification_person_fk foreign key (person_id) references person (id),
    constraint notification_doc_simple_fk foreign key (doc_id) references doc_simple,
    constraint notification_task_fk foreign key (task_id) references task
);

create table person_search_filter
(
    id               bigserial primary key,
    create_date      timestamp,
    favorite         boolean not null,
    filter           varchar(255),
    name             varchar(255),
    create_person_id bigint,

    constraint person_search_filter_person_fk
        foreign key (create_person_id) references person
);
