-- Remove old rows.

DELETE FROM file_image;

-- Ensure the file_image primary key can be auto-incremented and start from 1

ALTER TABLE file_image MODIFY COLUMN file_id INT NOT NULL UNIQUE AUTO_INCREMENT FIRST;
ALTER TABLE file_image AUTO_INCREMENT = 1;

-- Insert new rows, after making sure that valid antenna and measurement rows exist.

INSERT INTO file_image
			(antenna_code,
			antenna_type_code,
			date_uploaded,
			file_name,
			measurement_code,
			number_of_records,
			status)
VALUES ('ANT001', 'B', NOW(), 'ant001Measurements.csv', 'GAIN', 10, 'UPLOADED'),
	   ('ANT004', 'S', NOW(), 'ant004Measurements.csv', 'HPOLAR', 9, 'PUBLISHED'),
	   ('ANT003', 'B', NOW(), 'ant006Measurements.csv', 'VPOLAR', 12, 'REJECTED'),
	   ('ANT007', 'S', NOW(), 'ant006Measurements.csv', 'VSWR', 8, 'UPLOADED');
