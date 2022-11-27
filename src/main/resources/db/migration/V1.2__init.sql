create index person_login_idx on person (login);
create index attribute_option_attribute_type_idx on attribute_option (attribute_type_id);
create index catalog_ancestors_path_idx on catalog using gist (ancestors_path);
create index catalog_parent_catalog_idx on catalog (parent_catalog_id);
create index catalog_attribute_type_idx on catalog_attribute_type (attribute_type_id);
create index catalog_attribute_type_catalog_idx on catalog_attribute_type (catalog_id);
create index contact_person_idx on contact (person_id);
create index dictionary_data_value_idx on dictionary_data (value);
create index doc_simple_global_id_idx on doc_simple (global_id);
create index doc_simple_ancestor_catalogs_doc_simple_idx on doc_simple_ancestor_catalogs (doc_simple_id);
create index doc_simple_ancestor_catalogs_ancestor_catalog_idx on doc_simple_ancestor_catalogs (ancestor_catalog_id);
create index doc_simple_parent_catalogs_doc_simple_idx on doc_simple_parent_catalogs (doc_simple_id);
create index doc_simple_parent_catalogs_parent_catalog_idx on doc_simple_parent_catalogs (parent_catalog_id);
create index group_catalog_catalog_idx on group_catalog (catalog_id);
create index group_catalog_group_idx on group_catalog (group_id);
create index group_person_person_idx on group_person (person_id);
create index group_person_group_idx on group_person (group_id);
create index role_name_idx on role (name);
create index group_role_role_idx on group_role (role_id);
create index group_role_group_idx on group_role (group_id);
create index process_create_person_idx on process (create_person_id);
create index process_create_date_idx on process (create_date);
create index task_create_person_idx on task (create_person_id);
create index task_executor_person_idx on task (executor_person_id);
create index task_docs_doc_idx on task_docs (doc_id);
create index task_docs_orders_idx on task_docs (task_id);
create index notification_person_idx on notification (person_id);
create index person_search_filter_person_idx on person_search_filter (create_person_id);
