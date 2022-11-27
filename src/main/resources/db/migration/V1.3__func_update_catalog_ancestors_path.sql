create or replace function update_catalog_ancestors_path() returns trigger as
$$
declare
    path ltree;
begin
    if new.parent_catalog_id is null then
        new.ancestors_path = '0'::ltree || new.id::text;
    elseif tg_op = 'INSERT' or old.parent_catalog_id is null or old.parent_catalog_id != new.parent_catalog_id then
        select c.ancestors_path || new.id::text from catalog c where c.id = new.parent_catalog_id into path;
        if path is null then
            raise exception 'invalid parent_id %', new.parent_catalog_id;
        end if;
        new.ancestors_path = path;
    end if;
    return new;
end;
$$ language plpgsql;

create trigger ancestors_path_tgr
    before insert or update
    on catalog
    for each row
execute procedure update_catalog_ancestors_path();
