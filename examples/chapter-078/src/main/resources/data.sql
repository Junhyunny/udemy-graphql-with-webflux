DROP TABLE IF EXISTS customer;
CREATE TABLE customer
(
    id   INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(50),
    age  INT,
    city VARCHAR(50)
);

INSERT INTO customer (name, age, city)
VALUES ('Sam', 10, 'Atlanta'),
       ('Mike', 15, 'Hustom'),
       ('Jake', 20, 'Miami'),
       ('John', 30, 'New York')
;