create sequence hibernate_sequence start 1 increment 1;
 create table user_account (
       id int4 not null,
        password varchar(255) not null,
        created_at timestamp,
        first_name varchar(16),
        last_name varchar(16),
        role varchar(255),
        status varchar(255),
        user_name varchar(16) not null,
        primary key (id));
 alter table user_account
       add constraint UK_n581kgbdmb1ruhd047l51wn0a unique (user_name);