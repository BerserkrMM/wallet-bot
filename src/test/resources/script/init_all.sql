drop database if exists test;
create database test;

create table cards
(
    id      bigint not null
        primary key,
    balance numeric(19, 2),
    name    varchar(255) unique
);

create table transactions
(
    id      bigint         not null
        primary key,
    amount  numeric(19, 2) not null,
    type    varchar(255),
    card_id bigint
        constraint fkjxdscq0bxpy0pl465vvsqc89j
            references cards,
    timestamp timestamp default CURRENT_TIMESTAMP
);

create table Commands
(
    id   bigint       not null
        primary key,
    name varchar(255) not null
        constraint uk_sj9rerwuj7q00a7oy76efiehv
            unique
);

create table States
(
    id   bigint       not null
        primary key,
    name varchar(255) not null
        constraint uk_rdb7pnbo5e3l4vc5bkpk5q6t1
            unique
);

create table command_state_dependency
(
    id                bigint not null
        primary key,
    base_id           bigint
        constraint fkerjv4ayfkhw840r0tkpi3ounm
            references commands,
    command_id        bigint
        constraint fk3swo3tv5uw84gt0h15o42y8s3
            references commands,
    current_state_id  bigint
        constraint fkd4jr0wuyapa5sgd4vr19g7v9f
            references states,
    next_state_id     bigint
        constraint fkf7j5gs40ihsgf9i9h4dtc4e9h
            references states,
    previous_state_id bigint
        constraint fkahgr74c14r91lcfw431eyk5ts
            references states
);

create table current_condition
(
    id         bigint not null
        primary key,
    command_id bigint not null
        constraint uk_d6bvyng0m5wooggruq1kygv3c
            unique
        constraint fk94aseiloyag45c28srlpspwte
            references commands,
    state_id   bigint
        constraint fk7d9h2c3f6ml9lugnofv91qehu
            references states
);

create table card_draft
(
    id      bigint not null
        primary key,
    name    varchar(255),
    balance numeric(38, 2),
    status  varchar(30)
);

create table transaction_draft
(
    id      bigint         not null
        primary key,
    status  varchar(255),
    type    varchar(255),
    card_id bigint
        constraint fk3ng826qb34g3pri1j4hfqh8b8
            references cards,
    amount  numeric(19, 2)
);

create table command_state_message_history
(
    id        bigint not null
        primary key,
    message   varchar(255),
    timestamp timestamp default current_timestamp
);

create sequence hibernate_sequence;

insert into Commands (id, name)
values (1, '/start'),
       (2, 'Show balance'),
       (3, 'Create a card'),
       (4, 'Add income/expense'),
       (5, 'Show operation history'),
       (6, 'Back'),
       (7, 'Reset'),
       (8, 'Confirm creating card'),
       (9, 'Confirm creating operation');

insert into States (id, name)
values (1, 'NoState'),
       (2, 'SetName'),
       (3, 'SetBalance'),
       (4, 'SetAmount'),
       (5, 'SetType'),
       (6, 'ChooseCard'),
       (7, 'SetDateFrom'),
       (8, 'SetDateTo'),
       (9, 'Confirmation');

insert into command_state_dependency (id, command_id, base_id, current_state_id, next_state_id, previous_state_id)
VALUES (1 , 1, 1, 1, 1, 1),
       (2 , 2, 1, 1, 1, 1),
       (3 , 3, 1, 1, 2, 1),
       (4 , 3, 1, 2, 3, 1),
       (5 , 3, 1, 3, 1, 2),
       (6 , 3, 1, 9, 1, 3),
       (7 , 4, 1, 1, 6, 1),
       (8 , 4, 1, 6, 4, 1),
       (9 , 4, 1, 4, 5, 6),
       (10, 4, 1, 5, 1, 4),
       (11, 5, 1, 1, 6, 1),
       (12, 5, 1, 6, 7, 1),
       (13, 5, 1, 7, 8, 6),
       (14, 5, 1, 8, 1, 7),
       (15, 6, 1, 1, 1, 1),
       (16, 7, 1, 1, 1, 1),
       (17, 8, 1, 1, 1, 1);

insert into current_condition (id, command_id, state_id)
VALUES (1, 1, 1);

INSERT INTO cards (id, balance, name)
VALUES (1,100,'a'),
       (2,200,'b'),
       (3,300,'c');

INSERT INTO transactions (id, amount, type, card_id)
VALUES (1,100,'INCOME',1),
       (2,200,'INCOME',2),
       (3,300,'INCOME',3);