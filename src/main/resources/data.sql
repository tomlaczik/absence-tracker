INSERT INTO SUBJECT (NAME) VALUES ('Analizis');
INSERT INTO SUBJECT (NAME) VALUES ('Analizis 2');

INSERT INTO LESSON (TIME, WEEKDAY) VALUES (PARSEDATETIME('16:22', 'HH:mm'), 4);
INSERT INTO LESSON (TIME, WEEKDAY) VALUES (PARSEDATETIME('14:21', 'HH:mm'), 7);

INSERT INTO ABSENCE (LESSON_ID, WEEK) VALUES (1, 5);
INSERT INTO ABSENCE (LESSON_ID, WEEK) VALUES (2, 7);
