alter table catalog
    add column personal boolean default false;
alter table group_catalog
    add column can_edit boolean default false;
