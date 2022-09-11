INSERT INTO drones(serial_number, model, battery_capacity, state)
VALUES ('123', 'LIGHTWEIGHT', 55, 'IDLE');
INSERT INTO drones(serial_number, model, battery_capacity, state)
VALUES ('234', 'LIGHTWEIGHT', 20, 'IDLE');
INSERT INTO drones(serial_number, model, battery_capacity, state)
VALUES ('345', 'MIDDLEWEIGHT', 80, 'RETURNING');
INSERT INTO drones(serial_number, model, battery_capacity, state)
VALUES ('456', 'CRUISERWEIGHT', 80, 'LOADED');
INSERT INTO drones(serial_number, model, battery_capacity, state)
VALUES ('567', 'HEAVYWEIGHT', 80, 'DELIVERING');
INSERT INTO drones(serial_number, model, battery_capacity, state)
VALUES ('678', 'HEAVYWEIGHT', 60, 'IDLE');

INSERT INTO medications(name, weight, code, picture)
VALUES ('ibuprofen', 40, 'IBP_0101', 'https://drsilvasultrawellness.com/wp-content/uploads/2019/04/00749603_dghl_ibuprofen_brown_500ct.jpg');
INSERT INTO medications(name, weight, code, picture)
VALUES ('vitamin c', 70, 'VTC_0102', 'https://antiagingsshop.com/wp-content/uploads/2016/11/1262-VITAMIN-C-CHEWABLE-500MG-Masticable-Sabor-Naranja.jpg');
INSERT INTO medications(name, weight, code, picture)
VALUES ('Viagra', 180, 'VGR_0103', 'https://www.drugs.com/images/pills/nlm/675440356.jpg');
INSERT INTO medications(name, weight, code, picture)
VALUES ('Xanax', 500, 'XNX_0104', 'https://www.drugs.com/images/pills/nlm/000090094.jpg');


--LIGHTWEIGHT, MIDDLEWEIGHT, CRUISERWEIGHT, HEAVYWEIGHT);
--IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING