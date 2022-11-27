create table facility_simple
(
    id                        bigserial primary key,
    guid                      varchar(36),
    create_date               timestamp without time zone not null default now(),
    create_person_id          bigint,
    actual                    boolean                     not null,
    idx                       integer                     not null,
    parent_guid               varchar(36),
    name                      varchar(512)                not null,
    building_structures_guid  varchar(36),
    station_number            varchar(255),
    factory_number            varchar(255),
    facility_type_guid        varchar(255),
    factory_manufacturer_guid varchar(255),
    manufacture_year          integer,
    commissioning_year        integer,
    passport_availability     integer,
    product_type_guid         varchar(36),
    office_branch_guid        varchar(36),


    constraint facility_person_fk foreign key (create_person_id) references person (id)
--         constraint building_structures_fk foreign key (building_structures_guid) references building_structures (guid)
--         constraint facility_type_fk foreign key (facility_type_guid) references facility_type (guid)
--         constraint factory_manufacturer_fk foreign key (factory_manufacturer_guid) references factory_manufacturer (guid)
--         constraint product_type_fk foreign key (product_type_guid) references product_type (guid)
--         constraint office_branch_fk foreign key (office_branch_guid) references office_branch (guid)
);

create index facility_parent_facility_idx on facility_simple (parent_guid);


create table group_facility
(
    id          bigserial primary key,
    group_id    bigint not null,
    facility_id bigint not null,

    constraint group_facility_catalog_fk
        foreign key (facility_id) references facility_simple,
    constraint group_facility_group_fk
        foreign key (group_id) references groups,
    constraint group_facility_unique
        unique (group_id, facility_id)
);

create index group_facility_facility_idx on group_facility (facility_id);
create index group_facility_group_idx on group_facility (group_id);
