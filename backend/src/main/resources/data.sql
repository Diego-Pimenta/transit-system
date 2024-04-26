INSERT INTO user (id, cpf, name, address, phone_number, email, password)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), '12345678901', 'John Doe', '123 Main St', '123-456-7890', 'john@example.com', 'password123');
INSERT INTO user (id, cpf, name, address, phone_number, email, password)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), '12345678902', 'Jane Smith', '456 Elm St', '987-654-3210', 'jane@example.com', 'securepassword');
INSERT INTO user (id, cpf, name, address, phone_number, email, password)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), '12345678903', 'Alice Johnson', '789 Oak Ave', '555-123-4567', 'alice@example.com', 'abc123');
INSERT INTO user (id, cpf, name, address, phone_number, email, password)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), '12345678904', 'Bob Brown', '101 Pine Rd', '888-222-3333', 'bob@example.com', 'qwerty');
INSERT INTO user (id, cpf, name, address, phone_number, email, password)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), '12345678905', 'Charlie Green', '246 Cedar Ln', '444-555-6666', 'charlie@example.com', 'pass123');

INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'ABC123', 'Toyota Corolla', 'Red', 2019);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'XYZ789', 'Honda Civic', 'Blue', 2018);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'DEF456', 'Ford Mustang', 'Black', 2020);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'GHI789', 'Chevrolet Silverado', 'White', 2021);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'JKL321', 'Jeep Wrangler', 'Green', 2017);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'MNO456', 'Tesla Model S', 'Silver', 2022);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'PQR789', 'BMW X5', 'Gray', 2016);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'STU012', 'Audi Q7', 'Brown', 2023);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'VWX345', 'Mercedes-Benz C-Class', 'Gold', 2015);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'YZA678', 'Subaru Outback', 'Orange', 2024);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'BCD901', 'Lexus RX', 'Purple', 2014);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'EFG234', 'Volkswagen Golf', 'Yellow', 2025);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'HIJ567', 'Hyundai Sonata', 'Turquoise', 2013);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'KLM890', 'Mazda CX-5', 'Beige', 2026);
INSERT INTO vehicle (id, plate, model, color, year)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'NOP123', 'Kia Sportage', 'Maroon', 2012);

INSERT INTO ticket (id, category, description, cost, date_time)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Speeding', 'Exceeded speed limit by 20 mph', 150.00, '2024-04-20 09:15:00');
INSERT INTO ticket (id, category, description, cost, date_time)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Red Light Violation', 'Crossed the intersection after the light turned red', 200.00, '2024-04-21 12:30:00');
INSERT INTO ticket (id, category, description, cost, date_time)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Illegal Parking', 'Parked in a no-parking zone', 100.00, '2024-04-22 15:45:00');
INSERT INTO ticket (id, category, description, cost, date_time)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Distracted Driving', 'Texting while driving', 250.00, '2024-04-23 17:00:00');
INSERT INTO ticket (id, category, description, cost, date_time)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Seatbelt Violation', 'Driver not wearing a seatbelt', 120.00, '2024-04-24 14:20:00');
INSERT INTO ticket (id, category, description, cost, date_time)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Failure to Stop at a Stop Sign', 'Did not come to a complete stop at the stop sign', 180.00, '2024-04-25 11:10:00');
INSERT INTO ticket (id, category, description, cost, date_time)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Expired License', 'Driving with an expired license', 300.00, '2024-04-26 16:55:00');
INSERT INTO ticket (id, category, description, cost, date_time)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Reckless Driving', 'Swerving in and out of lanes', 400.00, '2024-04-27 13:40:00');
INSERT INTO ticket (id, category, description, cost, date_time)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Improper Lane Change', 'Changing lanes without signaling', 160.00, '2024-04-28 10:05:00');
INSERT INTO ticket (id, category, description, cost, date_time)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Driving Under the Influence', 'Caught driving while intoxicated', 500.00, '2024-04-29 18:25:00');
