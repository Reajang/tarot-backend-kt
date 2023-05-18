create table if not exists job
(
    id          uuid PRIMARY KEY,
    create_date timestamp    not null default now(),
    update_date timestamp,
    type        varchar(100) not null,
    status      varchar(100) not null
);


create table if not exists job_result
(
    id          uuid PRIMARY KEY,
    create_date timestamp                not null default now(),
    update_date timestamp,
    job_id      uuid references job (id) not null,
    data        varchar                  not null,
    type        varchar(2000)
);