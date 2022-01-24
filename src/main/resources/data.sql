insert into User (id, name, email, address, creation_date) values (1, 'name01', 'email01@gmail.com', 'address01', NOW());
insert into User (id, name, email, address, creation_date) values (2, 'name02', 'email02@gmail.com', 'address02', NOW());
insert into User (id, name, email, address, creation_date) values (3, 'name03', 'email03@gmail.com', 'address03', NOW());
insert into User (id, name, email, address, creation_date) values (4, 'name04', 'email04@gmail.com', 'address04', NOW());
insert into User (id, name, email, address, creation_date) values (5, 'name05', 'email05@gmail.com', 'address05', NOW());

insert into Account (id, number, balance, deleted, user_id, creation_date) values (1, '111', 10, false, 1, NOW());
insert into Account (id, number, balance, deleted, user_id, creation_date) values (2, '222', 20, false, 2, NOW());
insert into Account (id, number, balance, deleted, user_id, creation_date) values (3, '333', 30, false, 3, NOW());
insert into Account (id, number, balance, deleted, user_id, creation_date) values (4, '444', 40, false, 4, NOW());
insert into Account (id, number, balance, deleted, user_id, creation_date) values (5, '555', 50, false, 5, NOW());
insert into Account (id, number, balance, deleted, user_id, creation_date) values (6, '666', 60, false, 1, NOW());
insert into Account (id, number, balance, deleted, user_id, creation_date) values (7, '777', 0, true, 1, NOW());


insert into Transaction (id, amount, account_id, withdrawal_account_from_id, type, creation_date) values (1, 10, 1, null, 'DEPOSIT', NOW());
insert into Transaction (id, amount, account_id, withdrawal_account_from_id, type, creation_date) values (2, 20, 2, null, 'DEPOSIT', NOW());
insert into Transaction (id, amount, account_id, withdrawal_account_from_id, type, creation_date) values (3, 30, 3, null, 'DEPOSIT', NOW());
insert into Transaction (id, amount, account_id, withdrawal_account_from_id, type, creation_date) values (4, 40, 4, null, 'DEPOSIT', NOW());
insert into Transaction (id, amount, account_id, withdrawal_account_from_id, type, creation_date) values (5, 120, 5, null,  'DEPOSIT', NOW());
insert into Transaction (id, amount, account_id, withdrawal_account_from_id, type, creation_date) values (6, 70, 6, 5, 'TRANSFER', NOW());
insert into Transaction (id, amount, account_id, withdrawal_account_from_id, type, creation_date) values (7, 10, 6, null,  'WITHDRAWAL', NOW());
insert into Transaction (id, amount, account_id, withdrawal_account_from_id, type, creation_date) values (8, 10, 7, null,  'DEPOSIT', NOW());
insert into Transaction (id, amount, account_id, withdrawal_account_from_id, type, creation_date) values (9, 10, 7, null, 'WITHDRAWAL', NOW());
