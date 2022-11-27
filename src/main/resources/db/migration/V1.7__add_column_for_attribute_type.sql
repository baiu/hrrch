alter table attribute_type
    add guid varchar(63);
alter table attribute_type
    rename column active to actual;
