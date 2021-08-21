-- Remove old rows.

DELETE FROM data;

-- Ensure the data primary key can be auto-incremented and start from 1

ALTER TABLE data MODIFY COLUMN data_id INT NOT NULL UNIQUE AUTO_INCREMENT FIRST;
ALTER TABLE data AUTO_INCREMENT = 1;

-- Insert new rows, after making sure that valid antenna and measurement rows exist.

INSERT INTO data
			(antenna_code,
			measurement_code,
			determinant,
			value,
			date_created)
VALUES	('ANT001', 'GAIN', 100, -9, NOW()),
		('ANT001', 'GAIN', 110, -6, NOW()),
		('ANT001', 'GAIN', 130, 0, NOW()),
		('ANT001', 'GAIN', 140, -3, NOW()),
		('ANT001', 'GAIN', 150, -6, NOW()),
		('ANT001', 'GAIN', 160, -9, NOW()),
		('ANT002', 'HPOLAR', 100, -9, NOW()),
		('ANT002', 'HPOLAR', 110, -6, NOW()),
		('ANT002', 'HPOLAR', 130, 0, NOW()),
		('ANT002', 'HPOLAR', 140, -3, NOW()),
		('ANT002', 'HPOLAR', 150, -6, NOW()),
		('ANT002', 'HPOLAR', 160, -9, NOW()),
		('ANT003', 'VPOLAR', 100, -9, NOW()),
		('ANT003', 'VPOLAR', 110, -6, NOW()),
		('ANT003', 'VPOLAR', 130, 0, NOW()),
		('ANT003', 'VPOLAR', 140, -3, NOW()),
		('ANT003', 'VPOLAR', 150, -6, NOW()),
		('ANT003', 'VPOLAR', 160, -9, NOW()),
		('ANT004', 'HPOLAR', 100, -9, NOW()),
		('ANT004', 'HPOLAR', 110, -6, NOW()),
		('ANT004', 'HPOLAR', 130, 0, NOW()),
		('ANT004', 'HPOLAR', 140, -3, NOW()),
		('ANT004', 'HPOLAR', 150, -6, NOW()),
		('ANT004', 'HPOLAR', 160, -9, NOW()),
		('ANT005', 'VSWR', 100, -9, NOW()),
		('ANT005', 'VSWR', 110, -6, NOW()),
		('ANT005', 'VSWR', 130, 0, NOW()),
		('ANT005', 'VSWR', 140, -3, NOW()),
		('ANT005', 'VSWR', 150, -6, NOW()),
		('ANT005', 'VSWR', 160, -9, NOW()),
		('ANT007', 'VSWR', 100, -9, NOW()),
		('ANT007', 'VSWR', 110, -6, NOW()),
		('ANT007', 'VSWR', 130, 0, NOW()),
		('ANT007', 'VSWR', 140, -3, NOW()),
		('ANT007', 'VSWR', 150, -6, NOW()),
		('ANT007', 'VSWR', 160, -9, NOW());
