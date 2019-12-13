insert  into user_role (role_name) values ('ADMIN'), ('ANONYMOUS'), ('MODERATOR'), ('SPEAKER'), ('USER');
insert into rights (right_title) values ('EDIT USER'), ('DELETE USER'), ('CREATE REPORT'), ('EDIT REPORT'), ('CREATE CONFERENCE'), ('EDIT CONFERENCE'), ('DELETE CONFERENCE');
insert into role_rights_relation values (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (3, 3), (3, 4), (4, 4);
insert into users (login, user_password, first_name, last_name, email, user_role)
    values ('user', '12345', 'Mart', 'Poom', 'Poom@gmail.com', 5),
       ('referee', '12345', 'Howard', 'Webb', 'HowardWeb@gmail.com', 3),
       ('AcountAdmin', '12345', 'Harold', 'Chapman', 'Harold@gmail.com', 1),
       ('speaker', '12345', 'Hovard', 'Lovecraft', 'lovecraft@gmail.com', 4);
insert into speaker(login, password, first_name, last_name, email, speaker_role, rating, bonuses) values ('speaker', '12345', 'Hovard', 'Lovecraft', 'lovecraft@gmail.com', 4, 50, 567);
insert into conferences(theme, date_time_planned, date_time_happened, address)
    values('devoxx ukraine 2019', '2019-10-01 10:00', '2019-10-01 10:00', 'Kyiv, Mezhyhirska St, 82, Event space M82');
insert into reports (title, time_start, speaker_id, conference_id, registered)
    values ('Methodology & Culture', '10:10:00', 1, 1, 50 );
