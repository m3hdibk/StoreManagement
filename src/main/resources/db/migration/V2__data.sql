insert into scheme (description, name, status, type, id, defaultItem) values ('Default Location', 'Default', true, 1, nextval('scheme_id_seq'), true);
insert into scheme (description, name, status, type, id, defaultItem) values ('Default Category', 'Default', true, 2, nextval('scheme_id_seq') , true);
insert into scheme (description, name, status, type, id, defaultItem) values ('Default Brand', 'Default', true, 3, nextval('scheme_id_seq'), true);
insert into users (name, id, type, phone_number) values ('Default', nextval('users_id_seq'), 1, 22);
insert into tax(id, amount, amount_Type, beforevat, type) values (nextval('tax_id_seq'), 7, 0, false, 1);
insert into tax(id, amount, amount_Type, beforevat, type) values (nextval('tax_id_seq'), 1, 1, false, 2);