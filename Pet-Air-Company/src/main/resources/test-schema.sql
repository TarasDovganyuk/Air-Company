drop table if exists flights;
drop table if exists airplanes;
drop table if exists air_companies;

create table air_companies
(
    id           bigint generated by default as identity
        constraint air_companies_pkey
        primary key,
    company_type varchar(255) not null,
    founded_at   timestamp,
    name         varchar(255) not null
);

alter table air_companies
    owner to postgres;

create table airplanes
(
    id                bigint generated by default as identity
        constraint airplanes_pkey
        primary key,
    created_at        timestamp,
    serial_number     uuid,
    flight_distance   bigint,
    fuel_capacity     bigint       not null,
    name              varchar(255) not null,
    number_of_flights bigint,
    air_company_id    bigint
        constraint fksnx5fllu72d38u8e7cghe6ofq
        references air_companies
);

create table flights
(
    id                    bigint generated by default as identity
        constraint flights_pkey
        primary key,
    created_at            timestamp,
    delay_started_at      timestamp,
    destination_country   varchar(255) not null,
    distance              bigint       not null,
    ended_at              timestamp,
    estimated_flight_time bigint       not null,
    flight_status         varchar(255),
    started_at            timestamp,
    air_company_id        bigint       not null
        constraint fk4dg00qjfx5f8hw79i6ebdhluu
        references air_companies,
    airplane_id           bigint       not null
        constraint fkg5m8ip0f64g1nm42q8bf6s5ak
        references airplanes
);
