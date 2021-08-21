-- Remove old rows.

DELETE FROM antenna_type;

-- Ensure the antenna_type primary key can be auto-incremented and start from 1

ALTER TABLE antenna_type MODIFY COLUMN antenna_type_id INT NOT NULL UNIQUE AUTO_INCREMENT FIRST;
ALTER TABLE antenna_type AUTO_INCREMENT = 1;

-- The antenna_type table must have values in it before inserting values into ant_antenna.

INSERT INTO antenna_type
			(antenna_type_code,
			date_created,
			description,
			name)
VALUES 	('B', NOW(), 'Types an antenna as a base antenna that may contain no or some sub-antennas.', 'Base'),
		('S', NOW(), 'Types an antenna as a sub-antenna of a base antenna that contains it.', 'Sub-Antenna');

-- Remove old rows.

DELETE FROM antenna;	

-- Ensure the antenna primary key can be auto-incremented and start from 1

ALTER TABLE antenna MODIFY COLUMN antenna_id INT NOT NULL UNIQUE AUTO_INCREMENT FIRST;		
ALTER TABLE antenna AUTO_INCREMENT = 1;

-- Combinations of container, base and sub antenna rows. 

INSERT INTO antenna
			(antenna_code,
			date_created,
			description,
			name,
			main_antenna_code,
			antenna_type_code)
VALUES	('ANT001', NOW(), 'Base antenna without sub-antennas', 'ANT001-SA1', 'ANT001', 'B'),
		('ANT002', NOW(), 'Base antenna without sub-antennas', 'ANT002-SA2', 'ANT002', 'B'),
		('ANT003', NOW(), 'Base antenna with sub-antennas', 'ANT001-SA3', 'ANT003', 'B'),
		('ANT004', NOW(), 'Sub-antenna of ANT003', 'ANT001-SA4', 'ANT003', 'S'),
		('ANT005', NOW(), 'Sub-antenna of ANT003', 'ANT001-SA5', 'ANT003', 'S'),
		('ANT006', NOW(), 'Base antenna with sub-antennas', 'ANT001-SA6', 'ANT006', 'B'),
		('ANT007', NOW(), 'Sub-antenna of ANT006', 'ANT001-SA7', 'ANT006', 'S');
