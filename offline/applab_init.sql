CREATE DATABASE IF NOT EXISTS AppLab;
USE AppLab;

CREATE TABLE IF NOT EXISTS Employee (
  Eid INT PRIMARY KEY,
  Ename VARCHAR(100) NOT NULL,
  Dept VARCHAR(100) NOT NULL,
  Doj DATE NOT NULL,
  Salary DOUBLE NOT NULL
);

INSERT INTO Employee (Eid, Ename, Dept, Doj, Salary)
SELECT 1, 'John Doe', 'IT', '2022-01-01', 50000.00
WHERE NOT EXISTS (SELECT 1 FROM Employee WHERE Eid = 1);
INSERT INTO Employee (Eid, Ename, Dept, Doj, Salary)
SELECT 2, 'Jane Smith', 'HR', '2022-02-01', 60000.00
WHERE NOT EXISTS (SELECT 1 FROM Employee WHERE Eid = 2);
INSERT INTO Employee (Eid, Ename, Dept, Doj, Salary)
SELECT 3, 'Alice Johnson', 'Finance', '2022-03-01', 55000.00
WHERE NOT EXISTS (SELECT 1 FROM Employee WHERE Eid = 3);

CREATE TABLE IF NOT EXISTS BOOK (
  bid INT AUTO_INCREMENT PRIMARY KEY,
  bname VARCHAR(255) NOT NULL,
  author VARCHAR(255) NOT NULL,
  isbn VARCHAR(64) NOT NULL,
  price DOUBLE NOT NULL
);

INSERT INTO BOOK (bname, author, isbn, price)
SELECT 'To Kill a Mockingbird', 'Harper Lee', '978', 10.99
WHERE NOT EXISTS (
  SELECT 1 FROM BOOK WHERE bname = 'To Kill a Mockingbird' AND author = 'Harper Lee'
);
INSERT INTO BOOK (bname, author, isbn, price)
SELECT '1984', 'George Orwell', '978', 9.99
WHERE NOT EXISTS (
  SELECT 1 FROM BOOK WHERE bname = '1984' AND author = 'George Orwell'
);

CREATE TABLE IF NOT EXISTS Topics (
  topicID INT AUTO_INCREMENT PRIMARY KEY,
  topicName VARCHAR(100) NOT NULL UNIQUE
);

INSERT INTO Topics (topicName)
SELECT 'Java' WHERE NOT EXISTS (SELECT 1 FROM Topics WHERE topicName = 'Java');
INSERT INTO Topics (topicName)
SELECT 'Python' WHERE NOT EXISTS (SELECT 1 FROM Topics WHERE topicName = 'Python');
INSERT INTO Topics (topicName)
SELECT 'JavaScript' WHERE NOT EXISTS (SELECT 1 FROM Topics WHERE topicName = 'JavaScript');

CREATE TABLE IF NOT EXISTS FAQ (
  faqID INT AUTO_INCREMENT PRIMARY KEY,
  topicID INT NOT NULL,
  question VARCHAR(255) NOT NULL,
  answer TEXT NOT NULL,
  CONSTRAINT fk_faq_topic FOREIGN KEY (topicID) REFERENCES Topics(topicID)
);

INSERT INTO FAQ (topicID, question, answer)
SELECT t.topicID, 'What is Java?', 'Java is a high-level programming language developed by Sun Microsystems.'
FROM Topics t
WHERE t.topicName = 'Java'
  AND NOT EXISTS (SELECT 1 FROM FAQ WHERE question = 'What is Java?');
INSERT INTO FAQ (topicID, question, answer)
SELECT t.topicID, 'What is Python?', 'Python is an interpreted, high-level, general-purpose programming language.'
FROM Topics t
WHERE t.topicName = 'Python'
  AND NOT EXISTS (SELECT 1 FROM FAQ WHERE question = 'What is Python?');

CREATE TABLE IF NOT EXISTS STUDENT (
  Rollno VARCHAR(20) PRIMARY KEY,
  Name VARCHAR(120) NOT NULL,
  Branch VARCHAR(80) NOT NULL,
  `Year` INT NOT NULL,
  Cgpa DECIMAL(4,2) NOT NULL,
  DOB DATE NOT NULL,
  EmailID VARCHAR(160) NOT NULL
);

CREATE TABLE IF NOT EXISTS COURSES (
  Cid VARCHAR(20) PRIMARY KEY,
  Cname VARCHAR(120) NOT NULL,
  FacultyName VARCHAR(120) NOT NULL
);

CREATE TABLE IF NOT EXISTS COURSE_TAKEN (
  Rollno VARCHAR(20) NOT NULL,
  Cid VARCHAR(20) NOT NULL,
  PRIMARY KEY (Rollno, Cid),
  CONSTRAINT fk_course_taken_student FOREIGN KEY (Rollno) REFERENCES STUDENT(Rollno),
  CONSTRAINT fk_course_taken_course FOREIGN KEY (Cid) REFERENCES COURSES(Cid)
);

CREATE TABLE IF NOT EXISTS PATIENT (
  Pid VARCHAR(20) PRIMARY KEY,
  Pname VARCHAR(120) NOT NULL,
  DOB DATE NOT NULL,
  ContactNo VARCHAR(20) NOT NULL,
  Address VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS DIAGNOSIS (
  Did VARCHAR(20) PRIMARY KEY,
  Dname VARCHAR(120) NOT NULL,
  Medication VARCHAR(255) NOT NULL,
  Department VARCHAR(120) NOT NULL
);

CREATE TABLE IF NOT EXISTS TREATMENT (
  Pid VARCHAR(20) NOT NULL,
  Type VARCHAR(120) NOT NULL,
  Did VARCHAR(20) NOT NULL,
  DoctorName VARCHAR(120) NOT NULL,
  PRIMARY KEY (Pid, Did),
  CONSTRAINT fk_treatment_patient FOREIGN KEY (Pid) REFERENCES PATIENT(Pid),
  CONSTRAINT fk_treatment_diag FOREIGN KEY (Did) REFERENCES DIAGNOSIS(Did)
);

CREATE USER IF NOT EXISTS 'user'@'localhost' IDENTIFIED BY 'pass';
GRANT ALL PRIVILEGES ON AppLab.* TO 'user'@'localhost';
FLUSH PRIVILEGES;
