USE onewaytickettravel;

-- Czyszczenie danych
DELETE FROM offers;
ALTER TABLE offers AUTO_INCREMENT = 1;

DELETE FROM countries;
ALTER TABLE countries AUTO_INCREMENT = 1;

DELETE FROM continents;
ALTER TABLE continents AUTO_INCREMENT = 1;

-- Wstawianie kontynentów
INSERT INTO continents (name) VALUES ('Europa'), ('Azja'), ('Afryka');

-- Wstawianie krajów
INSERT INTO countries (name, continent_id)
VALUES
    ('Włochy', (SELECT id FROM continents WHERE name = 'Europa')),
    ('Hiszpania', (SELECT id FROM continents WHERE name = 'Europa')),
    ('Tajlandia', (SELECT id FROM continents WHERE name = 'Azja')),
    ('Maroko', (SELECT id FROM continents WHERE name = 'Afryka')),
    ('Egipt', (SELECT id FROM continents WHERE name = 'Afryka'));

-- Wstawianie ofert
INSERT INTO offers (name, price, continent_id, country_id)
VALUES
    ('Włochy Słońce i Pizza', 2500.50,
        (SELECT id FROM continents WHERE name = 'Europa'),
        (SELECT id FROM countries WHERE name = 'Włochy')),

    ('Hiszpania Tydzień Fiesta', 3200.00,
        (SELECT id FROM continents WHERE name = 'Europa'),
        (SELECT id FROM countries WHERE name = 'Hiszpania')),

    ('Tajlandia Rajskie Plaże', 4100.00,
        (SELECT id FROM continents WHERE name = 'Azja'),
        (SELECT id FROM countries WHERE name = 'Tajlandia')),

    ('Egipt All Inclusive', 1999.99,
        (SELECT id FROM continents WHERE name = 'Afryka'),
        (SELECT id FROM countries WHERE name = 'Egipt')),

    ('Maroko All Inclusive', 4999.99,
        (SELECT id FROM continents WHERE name = 'Afryka'),
        (SELECT id FROM countries WHERE name = 'Maroko'));
