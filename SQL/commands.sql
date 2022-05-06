USE `myintenetstore`;
INSERT INTO users (name, email, age) 
VALUES ('Dima', 'dima@i.ua', 23);
INSERT INTO users (name, email, age) 
VALUES ('Oleg', 'olegdate@gmail.com', 32);
INSERT INTO users (name, email, age) 
VALUES ('Vadim', 'vadim@mail.ru', 26);
INSERT INTO drivers (experience, user_id) 
VALUES ('Experienced', 3);
INSERT INTO delivery_workers (user_id) 
VALUES (2);
INSERT INTO costumers (phone_number, user_id) 
VALUES ('+38 066 870 56 41', 1);
INSERT INTO delivery_services (name, delivery_worker_id) 
VALUES ( 'k-company', 1);
INSERT INTO products (type) 
VALUES ( 'phone');
INSERT INTO products (type) 
VALUES ( 'computer');
INSERT INTO vehicles (type, `delivery_Service_id`, `driver_id`) 
VALUES ( 'car', 1, 1);
INSERT INTO orders (type, date, `delivery_service_id`, costumer_id) 
VALUES ( 'fast_delivery', '2022-05-03', 1, 1);
INSERT INTO storages (name, capacity) 
VALUES ( 'a-storage', 500);
INSERT INTO storages (name, capacity) 
VALUES ( 'b-storage', 1000);
INSERT INTO `vehicle_services` (name) 
VALUES ( 'a-service');
INSERT INTO `vehicle_services_maintain_vehicles` (`vehicle_id`, `vehicle_service_id`) 
VALUES (1, 1);
INSERT INTO  `delivery_services_maintane_storages` (`delivery_service_id`, `storage_id`) 
VALUES (1, 1);
INSERT INTO  `delivery_services_maintane_storages` (`delivery_service_id`, `storage_id`) 
VALUES (1, 2);
INSERT INTO  `orders_has_products`(`order_id`, `product_id`) 
VALUES (1, 1);
INSERT INTO  `orders_has_products`(`order_id`, `product_id`) 
VALUES (1, 2);
INSERT INTO  `storages_contain_products`(`storage_id`, `product_id`) 
VALUES (1, 1);
INSERT INTO  `storages_contain_products`(`storage_id`, `product_id`) 
VALUES (2, 2);
INSERT INTO  `vehicle_licences`(`number`, `data_of_experience`, `driver_id`) 
VALUES (23123, '2023-05-24', 1);
SELECT * FROM users;
UPDATE users SET email = 'dima@gmail.com' where id = 1;
SELECT * FROM users WHERE age > 23 AND name != 'Oleg';
UPDATE users SET name = 'Sergei' WHERE name = 'Oleg';
SELECT u.name, u.age, v.number, v.data_of_experience, d.experience FROM users u
JOIN drivers d on d.user_id = u.id
JOIN vehicle_licences v on v.driver_id = d.id;
UPDATE `vehicle_licences` SET number = 9872 WHERE id = 1;
SELECT * FROM storages WHERE capacity BETWEEN 400 AND 1100;
UPDATE storages SET capacity = 1101 WHERE id = 2;
SELECT * FROM products ORDER BY type;
UPDATE products SET type = 'vape' where type = 'computer';
SELECT u.name, u.email, u.age, d.experience, c.phone_number FROM users u 
LEFT JOIN drivers d on d.user_id = u.id 
LEFT JOIN costumers c on c.user_id = u.id
LEFT JOIN delivery_workers del on del.user_id = u.id; 
SELECT * FROM users u
RIGHT JOIN drivers d on u.id = d.user_id;
SELECT * FROM users WHERE age = 23 OR age = 32;
INSERT INTO users (name, email, age) 
VALUES ('Sasha', 'sasha@rom.ua', 23);
SELECT COUNT(users.id), name FROM users group by age;
DELETE FROM users WHERE id = 4;
DELETE FROM storages WHERE capacity > 1000;
DELETE FROM drivers;
DELETE FROM products WHERE type = 'computer';
DELETE FROM `vehicle_services_maintain_vehicles` where id = 1;
DELETE FROM vehicle_services;
