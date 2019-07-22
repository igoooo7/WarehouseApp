USE db_warehouse;

INSERT INTO job_titles(job_title) 
VALUES
('inżynier automatyk'),
('inżynier mechanik'),
('technik'),
('manager'),
('inżynier sprzedaży'),
('zakupowiec');
SELECT * FROM job_titles;

INSERT INTO areas(area)
VALUES
('Magazyn'),
('Warsztat'),
('Regały przy wejściu');
SELECT * FROM areas;
 
INSERT INTO racks(rack)
VALUES
(1),
(2),
(3);
SELECT * FROM racks;

INSERT INTO shelfs(shelf)
VALUES
(1),
(2),
(3);
SELECT * FROM shelfs; 
 
INSERT INTO locations(area_id, rack_id, shelf_id) 
VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3);
SELECT * FROM locations;

INSERT INTO units(unit) 
VALUES
('sztuki'),
('metry'),
('opakowania');
SELECT * FROM units;

INSERT INTO user_rights(user_rights) 
VALUES
('odczyt'),
('zapis'),
('administracja');
SELECT * FROM user_rights;

INSERT INTO part_groups(part_group) 
VALUES
('kable'),
('śruby'),
('złącza pneumatyczne'),
('czujniki'),
('moduły I/O');
SELECT * FROM part_groups;

INSERT INTO manufacturers(manufacturer) 
VALUES
('MurrElektronik'),
('Phoenix Contact'),
('Lapp Kabel'),
('Siemens'),
('Festo'),
('Stasto');
SELECT * FROM manufacturers;

INSERT INTO users(user_rights_id, job_title_id, user_name, user_password, first_name, last_name) 
VALUES
(3, 1, 'Igoooo7', '111', ' Igor', 'Leskiewicz'),
(2, 1, 'Stefek', '222', 'Łukasz', 'Stefanowicz'),
(1, 2, 'Alko_Szufla', '333', 'Karol', 'Szufla');
SELECT * FROM users;


INSERT INTO parts(user_id, manufacturer_id, location_id, part_group_id, unit_id, order_code, product_code,
					part_name, description, quantity_min, quantity_max, creation_date)
VALUES
(1, 4, 1, 5, 1, 'Kod zamówieniowy 1', 'Kod produktu 1', 'Nazwa produktu 1', 'Opis produktu 1', 1, 2, '2019-05-31 15:11:05'),
(2, 1, 2, 4, 3, 'Kod zamówieniowy 2', 'Kod produktu 2', 'Nazwa produktu 2', 'Opis produktu 2', 5, 5, '2019-02-14 11:14:07'),
(3, 2, 3, 1, 2, 'Kod zamówieniowy 3', 'Kod produktu 3', 'Nazwa produktu 3', 'Opis produktu 3', 7, 10, '2016-07-05 13:12:03'),
(1, 3, 1, 2, 1, 'Kod zamówieniowy 4', 'Kod produktu 4', 'Nazwa produktu 4', 'Opis produktu 4', 10, 25, '2019-06-15 08:16:05'),
(2, 4, 2, 3, 3, 'Kod zamówieniowy 5', 'Kod produktu 5', 'Nazwa produktu 5', 'Opis produktu 5', 2, 3, '2019-05-14 16:15:01');

SELECT manufacturer, part_name, description, order_code, product_code, part_group, area, rack, shelf, unit, quantity_min, quantity_max, creation_date, user_name, job_title
FROM parts
INNER JOIN manufacturers ON parts.manufacturer_id = manufacturers.manufacturer_id
INNER JOIN part_groups ON parts.part_group_id = part_groups.part_group_id
INNER JOIN units ON parts.unit_id = units.unit_id
INNER JOIN users ON parts.user_id = users.user_id

	INNER JOIN 
	(
		SELECT location_id , area FROM locations INNER JOIN areas ON locations.area_id = areas.area_id
	) 	
		AS A ON parts.location_id = A.location_id
	INNER JOIN 
	(
		SELECT location_id ,rack FROM locations INNER JOIN racks ON locations.rack_id = racks.rack_id
	) 	
		AS B ON parts.location_id = B.location_id
	INNER JOIN 
	(
		SELECT location_id ,shelf FROM locations INNER JOIN shelfs ON locations.shelf_id = shelfs.shelf_id
	) 	
		AS C ON parts.location_id = C.location_id
	INNER JOIN 
	(
		SELECT user_id , job_title FROM users INNER JOIN job_titles ON users.job_title_id = job_titles.job_title_id
	) 	
		AS D ON parts.user_id = D.user_id;
       
INSERT INTO projects(project_name)
VALUES
('PL_00807_RADIOMETER_OP-21414_Glu_lac_assembly_machine'),
('PL_00917_ZFTRW_OP-25488_Friction+NVH Prototype station for CD-EPS'),
('PL_00943_WABCO_OP-25692_EAPU2-CONN_2018');       
       
INSERT INTO orders(part_id, user_id, part_count, project_id, order_date) 
VALUES 
(1, 1, 4, 1, '2019-05-31 15:11:05'),
(1, 2, 2, 2, '2019-06-12 15:11:05'),
(2, 1, 8, 3, '2019-05-31 15:11:05');
 
 SELECT part_name, order_code, user_name, part_count, order_date, project_name
 FROM orders 
 INNER JOIN parts ON orders.part_id = parts.part_id
 INNER JOIN users ON orders.user_id = users.user_id
 INNER JOIN projects ON orders.project_id = projects.project_id;
 







