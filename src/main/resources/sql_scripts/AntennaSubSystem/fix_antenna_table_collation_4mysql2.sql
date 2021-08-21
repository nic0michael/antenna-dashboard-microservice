-- Check collation

SELECT 	table_schema,
		table_name,
        column_name,
        character_set_name,
        collation_name
FROM information_schema.columns
WHERE table_schema = 'antenna'
	  AND
	  collation_name <> 'utf8mb4_unicode_ci'
ORDER BY table_schema,
		 table_name,
         ordinal_position;
		 
-- Fix collation

ALTER TABLE <table_name_to_be_fixed>
CONVERT TO CHARACTER SET utf8mb4
COLLATE 'utf8mb4_unicode_ci';