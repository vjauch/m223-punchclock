insert into USER (FIRSTNAME, LASTNAME, USERNAME, PASSWORD) values
('Karl', 'Meister', 'kamei', '$2a$10$6Nr5L06hevPn1OR/nRBA2uHu4nx0OCZ0p8.4Suh4K83I1mivbJkpW'), --PW: password
('Lukas', 'Gattinger', 'luga', '$2a$10$bIM9BorfOhb3mkGaAddJ3utYA41t3e3QFtWFeR0EWMWiZJX0t6.3C'); --PW: geheim

insert into PROJECT (NAME) values
('Administration'),
('Events'),
('Entwicklung');

insert into ACTIVITY (NAME, FK_PROJECT) values
('Mails schreiben', 1),
('Arbeitsplatz einrichten', 1),
('Mitarbeitergespraech', 1),
('Team-Event', 2),
('Abteilungsessen', 2),
('Weihnachtsfeier', 2),
('Java Programmierung', 3),
('HTML & CSS', 3);

insert into ENTRY (CHECK_IN, CHECK_OUT, FK_ACTIVITY, FK_USER) values
('2020-09-03T07:23:00', '2020-09-03T16:23:00', 1, 1),
('2020-09-04T08:00:00', '2020-09-04T17:00:00', 3, 1),
('2020-09-05T07:23:00', '2020-09-05T16:23:00', 5, 2),
('2020-09-06T07:23:00', '2020-09-06T16:23:00', 7, 2);

