CREATE TABLE passenger (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
	address VARCHAR(100),
    phone_no VARCHAR(15)
);

CREATE TABLE ticket (
    id INT AUTO_INCREMENT PRIMARY KEY,
    route VARCHAR(100) NOT NULL,
    airline VARCHAR(100),
	price numeric(10,2)
);

CREATE TABLE food (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
	price numeric(8,2)
);

CREATE TABLE purchased_ticket (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ticket_id INT,
    passenger_id INT,
    ticket_count INT,
    total_price numeric(10,2),
    purchase_date datetime,
    FOREIGN KEY (ticket_id) REFERENCES ticket(id),
    FOREIGN KEY (passenger_id) REFERENCES passenger(id)
);


CREATE TABLE purchased_food (
    id INT AUTO_INCREMENT PRIMARY KEY,
    food_id INT,
    passenger_id INT,
    food_unit_count INT,
    total_price numeric(10,2),
    purchase_date datetime,
    FOREIGN KEY (food_id) REFERENCES food(id),
    FOREIGN KEY (passenger_id) REFERENCES passenger(id)
);



INSERT INTO passenger (name, email, address,phone_no) VALUES
('Ashik', 'ashik@gmail.com', 'Dhaka','123-456-7890'),
('Dip', 'dip@gmail.com', 'Khulna','098-765-4321');

INSERT INTO ticket (route, airline, price) VALUES
('Dhaka to Cox Bazar', 'Gorib Airlines', 3000.00),
('Cox Bazar to Dhaka', 'Gorib Airlines', 3000.00),
('Dhaka to Finland', 'Gorib Airlines', 50000.00),
('Finland to Dhaka', 'Gorib Airlines', 50000.00);

INSERT INTO food (name, price) VALUES
('Sandwich', 100.00),
('Salad', 75.50),
('Juice', 80.00);

INSERT INTO purchased_ticket (ticket_id, passenger_id, ticket_count, total_price, purchase_date) VALUES
(1, 1, 2, 6000.00, '2024-07-01 10:00:00'),
(2, 2, 1, 3000.00, '2024-07-02 11:00:00'),
(3, 2, 3, 150000.00, '2024-07-10 12:00:00');

INSERT INTO purchased_food (food_id, passenger_id, food_unit_count, total_price, purchase_date) VALUES
(1, 1, 2, 200.00, '2024-07-01 10:30:00'),
(2, 2, 1, 75.50, '2024-07-02 11:30:00'),
(3, 2, 3, 240.00, '2024-07-10 12:30:00');


SELECT t.route, SUM(pt.total_price) AS total_sale_amount
FROM airline.purchased_ticket pt
JOIN airline.ticket t ON pt.ticket_id = t.id
GROUP BY t.route
ORDER BY total_sale_amount DESC
LIMIT 3;