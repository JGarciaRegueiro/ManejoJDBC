DROP database if exists crud_jdbc;

CREATE database crud_jdbc;
USE crud_jdbc;

CREATE TABLE cars(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    year INT NOT NULL,
    km INT NOT NULL
);

CREATE TABLE passengers(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    weight FLOAT NOT NULL
);

CREATE TABLE carspassengers (
    id_car INT NOT NULL,
    id_passenger INT NOT NULL,
    FOREIGN KEY (id_car) REFERENCES cars(id) ON DELETE CASCADE,
    FOREIGN KEY (id_passenger) REFERENCES passengers(id) ON DELETE CASCADE
);

INSERT INTO cars (brand, model, year, km) VALUES
('Toyota', 'Corolla', 2018, 50000),
('Honda', 'Civic', 2019, 40000),
('Ford', 'Focus', 2020, 35000),
('Chevrolet', 'Malibu', 2017, 60000),
('Nissan', 'Altima', 2016, 70000),
('Hyundai', 'Elantra', 2021, 25000),
('Volkswagen', 'Jetta', 2015, 80000),
('Mazda', 'Mazda3', 2014, 90000),
('Subaru', 'Impreza', 2013, 100000),
('Kia', 'Forte', 2018, 55000);

INSERT INTO passengers (name, age, weight) VALUES
('Juan', 25, 70.5),
('Maria', 30, 65.2),
('Carlos', 28, 80.0),
('Laura', 35, 55.8),
('Pedro', 22, 75.3),
('Ana', 40, 68.9),
('David', 27, 90.2),
('Elena', 33, 62.5),
('Raul', 29, 78.1),
('Isabel', 31, 67.0);

INSERT INTO carspassengers (id_car, id_passenger) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);
