insert into currencytype (ct_name) values ('MILES');
insert into currencytype (ct_name) values ('RAND');
insert into currency (currency_amount, ct_id) values (0.00, 1);
insert into currency (currency_amount, ct_id) values (14.00, 2);
insert into member (member_firstname, member_lastname, member_dob, member_email, member_contact_nr, currency_id) values ('Rudi', 'Dreyer', '2000-05-24', 'rudidreyer7@gmail.com', '0767869466', 1);
insert into member (member_firstname, member_lastname, member_dob, member_email, member_contact_nr, currency_id) values ('Ruben', 'Dreyer', '2003-12-17', 'rubendreyer@gmail.com', '0768532197', 2);
insert into transaction (transaction_date, transaction_change, member_id) values ('2021-09-28', '+20', 1);