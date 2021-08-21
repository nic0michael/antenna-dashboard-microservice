-- Remove old rows.

DELETE FROM file_image_measurement;

-- Ensure the file_image_measurement primary key can be auto-incremented and start from 1

ALTER TABLE file_image_measurement MODIFY COLUMN file_image_measurement_id INT NOT NULL UNIQUE AUTO_INCREMENT FIRST;
ALTER TABLE file_image_measurement AUTO_INCREMENT = 1;

-- Insert new rows, after making sure that valid file_image rows exist.

INSERT INTO file_image_measurement
			(antenna_code,
			determinant,
			value)
VALUES ('ANT001', 100, 9),
	   ('ANT001', 110, 6),
	   ('ANT001', 120, 0),
	   ('ANT001', 130, 3),
	   ('ANT001', 140, 0),
	   ('ANT001', 150, 3),
	   ('ANT001', 160, 6),
	   ('ANT004', 100, 6),
	   ('ANT004', 110, 3),
	   ('ANT004', 120, 0),
	   ('ANT004', 130, 3),
	   ('ANT004', 140, 9),
	   ('ANT004', 150, 6),
	   ('ANT004', 160, 0),
	   ('ANT003', 100, 0),
	   ('ANT003', 110, 3),
	   ('ANT003', 120, 1),
	   ('ANT003', 130, 2),
	   ('ANT003', 140, 5),
	   ('ANT003', 150, 8),
	   ('ANT003', 160, 0),
	   ('ANT007', 100, 0),
	   ('ANT007', 110, 3),
	   ('ANT007', 120, 1),
	   ('ANT007', 130, 2),
	   ('ANT007', 140, 5),
	   ('ANT007', 150, 8),
	   ('ANT007', 160, 0);
