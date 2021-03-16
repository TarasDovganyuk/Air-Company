INSERT INTO air_companies (company_type, founded_at, name)
VALUES ('INTERNATIONAL', '2020-03-13T22:15:23.367449700', 'LUFTHANSA'),
       ('INTERNATIONAL', '2000-05-13T17:14:42.764412300', 'AUSTRIAN'),
       ('REGIONAL', '1967-01-01T12:30:00.000000000', 'FLY EMIRATES');

insert into airplanes (air_company_id, created_at, serial_number, flight_distance, fuel_capacity, name, number_of_flights)
values (1, '2010-01-01T15:00:00.000000000', '0cda669c-715a-4daf-bff6-3bc093009647', 0, 2000, 'Boeing', 0),
       (1, '2015-01-01T17:30:00.000000000', 'a7e572cc-b67e-48d6-aac1-29347b82ffc1', 0, 3000, 'Airbus', 0),
       (1, '2020-05-01T10:25:00.000000000', '8670eabd-3a71-4f1e-9511-26b41df23e28', 0, 10000, 'Mriya', 0),
       (2, '2017-01-01T11:40:00.000000000', 'e9af3185-c13f-4e21-a92a-bd66c5dba9d1', 0, 6000, 'Ruslan', 0),
       (2, '2013-05-01T16:35:00.000000000', '2561560e-71bd-47a6-a2ac-c06baf28cbac', 0, 1000, 'AN-171', 0),
       (2, '2000-11-07T18:00:00.000000000', '50bd7f0e-03c2-4227-ab71-7ca6543f3683', 0, 8000, 'Nadiya', 0),
       (3, '2020-12-10T21:30:00.000000000', 'cf04ef79-7dda-452c-a713-fdfceee6c5e3', 0, 5000, 'Boeing', 0),
       (3, '2003-09-09T14:45:00.000000000', '3afc8632-c605-4cdd-ad81-c031fffddd31', 0, 10000, 'Mriya', 0),
       (3, '2001-04-14T12:15:00.000000000', 'c0ee7024-49d0-455e-9f96-1e9ae87cbebe', 0, 6000, 'Ruslan', 0);

insert into flights (air_company_id, airplane_id, created_at, delay_started_at, destination_country, distance, ended_at,
                     estimated_flight_time, flight_status, started_at)
values (1, 1, '2021-03-13T22:23:04.288130400', null, 'USA', 5000, null, 8, 'PENDING', null),
       (1, 2, '2021-03-13T12:26:04.288130400', null, 'Brazil', 7000, null, 15, 'ACTIVE', '2021-03-13T12:00:04.288130400'),
       (1, 3, '2021-03-13T22:23:04.288130400', null, 'India', 6000, '2021-03-13T23:30:08.756130200', 10, 'COMPLETED',
        '2020-03-13T12:26:04.288130400'),
       (2, 4, '2021-03-13T22:23:04.288130400', null, 'Thailand', 6000, '2020-05-14T00:26:04.288130400', 15, 'COMPLETED',
        '2020-05-13T09:30:04.288130400'),
       (2, 4, '2021-03-13T22:23:04.288130400', null, 'Australia', 10000, '2021-01-11T15:20:04.677554400', 28, 'COMPLETED',
        '2021-01-10T10:30:04.573937500'),
       (2, 5, '2021-03-13T22:23:04.288130400', '2021-03-14T18:00:04.185365700', 'Poland', 1000, null, 2, 'DELAYED', null),
       (2, 6, '2021-03-13T22:23:04.288130400', null, 'Turkey', 2200, null, 4, 'ACTIVE', '2021-03-15T15:00:00.000000000'),
       (3, 7, '2021-03-13T22:23:04.288130400', null, 'France', 2500, '2019-08-20T06:46:23.288130400', 3, 'COMPLETED',
        '2019-08-20T02:35:14.234444300'),
       (3, 8, '2021-03-13T22:23:04.288130400', null, 'Spain', 3500, '2020-05-09T19:30:20.175837400', 5, 'COMPLETED',
        '2020-05-09T14:45:04.957383900'),
       (3, 9, '2021-03-13T22:23:04.288130400', '2021-03-15T16:15:00.2342333200', 'Canada', 4000, null, 9, 'DELAYED', null);