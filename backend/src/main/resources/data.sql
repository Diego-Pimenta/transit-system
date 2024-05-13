INSERT INTO user (id, cpf, name, address, phone_number, email, password, role)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), '12345678901', 'John Doe', '123 Main St', '123-456-7890', 'john@example.com', '$2a$10$8.YyOoFBqX0vumYoG.bU2.XxW1rogZ9HiJcBa7IwlPeGasBw.iLkm', 'WORKER');
INSERT INTO user (id, cpf, name, address, phone_number, email, password, role)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), '12345678902', 'Jane Smith', '456 Elm St', '987-654-3210', 'jane@example.com', '$2a$10$l0.KGvltAgzJ4zf/MMWxk.5AAd8EaQrFJgMPkCTcG9KdFRtxahrg.', 'USER');
INSERT INTO user (id, cpf, name, address, phone_number, email, password, role)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), '12345678903', 'Alice Johnson', '789 Oak Ave', '555-123-4567', 'alice@example.com', '$2a$10$vWHJnUxOwYYSmaMSV24.munWaOFiIX0xXqqM6v58RRrn.Uu.TmdIO', 'USER');
INSERT INTO user (id, cpf, name, address, phone_number, email, password, role)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), '12345678904', 'Bob Brown', '101 Pine Rd', '888-222-3333', 'bob@example.com', '$2a$10$NX82DfScLaahgDj53zsOeeK04z7MUyaJf7UVPwJS4H0x4QsBHclme', 'USER');
INSERT INTO user (id, cpf, name, address, phone_number, email, password, role)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), '12345678905', 'Charlie Green', '246 Cedar Ln', '444-555-6666', 'charlie@example.com', '$2a$10$uJJ9mQ.Zre74x4zfiqogBOSeU8F2SSUlsTfhazrglmiQeZ4AClppW', 'USER');

INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'MOU7919', 'Toyota Corolla', 'Red', 2019);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'NFB3086', 'Honda Civic', 'Blue', 2018);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'NET7890', 'Ford Mustang', 'Black', 2020);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'JAZ1848', 'Chevrolet Silverado', 'White', 2021);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'HQN4544', 'Jeep Wrangler', 'Green', 2017);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'GMA7327', 'Tesla Model S', 'Silver', 2022);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'HZF7104', 'BMW X5', 'Gray', 2016);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'MFC5702', 'Audi Q7', 'Brown', 2023);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'NEK1602', 'Mercedes-Benz C-Class', 'Gold', 2015);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'IFO2978', 'Subaru Outback', 'Orange', 2024);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'NCH5557', 'Lexus RX', 'Purple', 2014);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'LRV1403', 'Volkswagen Golf', 'Yellow', 2025);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'MUY9246', 'Hyundai Sonata', 'Turquoise', 2013);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'JMS4654', 'Mazda CX-5', 'Beige', 2026);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'JTI7979', 'Kia Sportage', 'Maroon', 2012);

INSERT INTO ticket (id, category, description, cost, emission_date)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Speeding', 'Exceeded speed limit by 20 mph', 150.00, '2024-04-20 09:15:00');
INSERT INTO ticket (id, category, description, cost, emission_date)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Red Light Violation', 'Crossed the intersection after the light turned red', 200.00, '2024-04-21 12:30:00');
INSERT INTO ticket (id, category, description, cost, emission_date)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Illegal Parking', 'Parked in a no-parking zone', 100.00, '2024-04-22 15:45:00');
INSERT INTO ticket (id, category, description, cost, emission_date)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Distracted Driving', 'Texting while driving', 250.00, '2024-04-23 17:00:00');
INSERT INTO ticket (id, category, description, cost, emission_date)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Seatbelt Violation', 'Driver not wearing a seatbelt', 120.00, '2024-04-24 14:20:00');
INSERT INTO ticket (id, category, description, cost, emission_date)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Failure to Stop at a Stop Sign', 'Did not come to a complete stop at the stop sign', 180.00, '2024-04-25 11:10:00');
INSERT INTO ticket (id, category, description, cost, emission_date)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Expired License', 'Driving with an expired license', 300.00, '2024-04-26 16:55:00');
INSERT INTO ticket (id, category, description, cost, emission_date)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Reckless Driving', 'Swerving in and out of lanes', 400.00, '2024-04-27 13:40:00');
INSERT INTO ticket (id, category, description, cost, emission_date)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Improper Lane Change', 'Changing lanes without signaling', 160.00, '2024-04-28 10:05:00');
INSERT INTO ticket (id, category, description, cost, emission_date)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Driving Under the Influence', 'Caught driving while intoxicated', 500.00, '2024-04-29 18:25:00');
