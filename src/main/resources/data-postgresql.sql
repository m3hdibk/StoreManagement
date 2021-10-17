insert into scheme (description, name, status, type, id) values ('Default Location', 'Default', true, 1, nextval('scheme_id_seq'));
insert into scheme (description, name, status, type, id) values ('Default Category', 'Default', true, 2, nextval('scheme_id_seq'));
insert into scheme (description, name, status, type, id) values ('Default Brand', 'Default', true, 3, nextval('scheme_id_seq'));
insert into client (name, id) values ('Default', nextval('client_id_seq'));
insert into tax(id, amount, amount_Type, beforevat, type) values (nextval('tax_id_seq'), 7, 0, false, 1);
insert into tax(id, amount, amount_Type, beforevat, type) values (nextval('tax_id_seq'), 1, 1, false, 2);