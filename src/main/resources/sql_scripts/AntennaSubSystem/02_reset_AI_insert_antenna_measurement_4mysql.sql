-- Remove old rows.

DELETE FROM measurement;

-- Ensure the measurement primary key can be auto-incremented and start from 1

ALTER TABLE measurement MODIFY COLUMN measurement_id INT NOT NULL UNIQUE AUTO_INCREMENT FIRST;
ALTER TABLE measurement AUTO_INCREMENT = 1;

-- Insert new rows.

INSERT INTO measurement
			(measurement_code,
			name,
			description,
			date_created)
VALUES 	('GAIN', 'Gain Measurement', 'Responds according to frequency.', NOW()),
		('HPOLAR', 'Horizontal Polar', 'Responds according to frequency.', NOW()),
		('VPOLAR', 'Vertical Polar', 'Responds according to frequency.', NOW()),
		('VSWR', 'Voltage Standing Wave Ratio', 'Responds according to frequency.', NOW());
