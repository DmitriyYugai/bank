drop table if exists transactions;
drop table if exists types;
drop table if exists accounts;
drop table if exists clients;

create table if not exists clients(
    id serial primary key,
    name text
);

create table if not exists accounts(
    number integer primary key,
    sum integer,
    client_id integer references clients(id)
);

create table if not exists types(
    id serial primary key,
    name text
);

create table if not exists transactions(
    id serial primary key,
    type_id integer references types(id),
    sum integer,
    client_id integer references clients(id),
    number integer references accounts(number),
    exch_number integer references accounts(number)
);

insert into types(name) values('Пополнить');
insert into types(name) values('Списать');
insert into types(name) values('Перевести');
