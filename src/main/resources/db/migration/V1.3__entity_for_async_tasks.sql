create table if not exists job
(
    id          uuid PRIMARY KEY,
    create_date timestamp    not null default now(),
    update_date timestamp,
    type        varchar(100) not null,
    status      varchar(100) not null
);