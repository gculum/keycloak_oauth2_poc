drop table if exists workout cascade;

create table workout (
    id serial not null,
    difficulty integer not null,
    end_time timestamp(6),
    start_time timestamp(6),
    user_id varchar(255),
    primary key (id)
);