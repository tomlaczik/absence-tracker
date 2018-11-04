--INSERT INTO USER (NAME) VALUES ('Kis Bela');
--INSERT INTO USER (NAME) VALUES ('Nagy Bela');
--INSERT INTO USER (NAME) VALUES ('Tanar');

insert into user (username, password, enabled, role) values ('user1', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', true, 'ADMIN');
insert into user (username, password, enabled, role) values ('user2', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', true, 'STUDENT');
insert into user (username, password, enabled, role) values ('user3', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', true, 'TEACHER'); 

INSERT INTO SUBJECT (NAME) VALUES ('Analizis');
INSERT INTO SUBJECT (NAME) VALUES ('Analizis 2');

INSERT INTO LESSON (TEACHER_ID, SUBJECT_ID, TIME, WEEKDAY) VALUES (3, 1, PARSEDATETIME('16:22', 'HH:mm'), 4);
INSERT INTO LESSON (TEACHER_ID, SUBJECT_ID, TIME, WEEKDAY) VALUES (3, 2, PARSEDATETIME('14:21', 'HH:mm'), 7);

INSERT INTO ABSENCE (USER_ID, LESSON_ID, WEEK) VALUES (1, 1, 5);
INSERT INTO ABSENCE (USER_ID, LESSON_ID, WEEK) VALUES (1, 1, 2);
INSERT INTO ABSENCE (USER_ID, LESSON_ID, WEEK) VALUES (2, 2, 7);
INSERT INTO ABSENCE (USER_ID, LESSON_ID, WEEK) VALUES (2, 2, 2);
--Nem ismeri fel a táblát, ez lenne a manytomany kapcsoló táblája elvileg
--INSERT INTO LESSON_USERS (ACTIVELESSONS_ID, STUDENTS_ID) VALUES (1,1);
--INSERT INTO LESSON_USERS (ACTIVELESSONS_ID, STUDENTS_ID) VALUES (2,2);