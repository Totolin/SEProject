-- USERS
INSERT INTO USER ( PASSWORD, STATE, TYPE_U, USERNAME) VALUES ('$2a$10$/pP9Za47JA15z2zs3.afW.a9Rq9qdJn3DBIzPHoPUjM5XBCT/auIi', 'Active', 'ADMIN', 'admin');

-- PERSONS
INSERT INTO PERSON(SSN, ADDRESS, EMAIL, FIRST_NAME, LAST_NAME, PHONE_NUMBER)
 VALUES('1940826160041','Bulevardul Bibescu, bloc A13', 'vladutu_georgian_4d@yahoo.com','Georgian','Vladutu','0770835787');

-- GROUPS
INSERT INTO GROUP_T(ID, SPECIALIZATION, YEAR) VALUES('10305S','C.E.',3);

-- STUDENTS
INSERT INTO STUDENT(SSN, GROUP_ID, SUBGROUP) VALUES ('1940826160041', '10305S', 'B');

-- SUBJECTS
INSERT INTO SUBJECT(ID, NAME, CREDITS) VALUES (1, 'Operating Systems', 5);
INSERT INTO SUBJECT(ID, NAME, CREDITS) VALUES (2, 'Software Engineering', 3);

--STUDENT_SUBJECTS
INSERT INTO STUDENT_SUBJECT(ID, GRADE, STUDENT_SSN, SUBJECT_ID) VALUES (1,10,'1940826160041',1);
INSERT INTO STUDENT_SUBJECT(ID, GRADE, STUDENT_SSN, SUBJECT_ID) VALUES (2,9,'1940826160041',2);
