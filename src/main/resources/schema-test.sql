CREATE DATABASE IF NOT EXISTS testone;

USE testone;

-- Tabela dla Kontynentów
CREATE TABLE IF NOT EXISTS continents (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE
) ENGINE=InnoDB;

-- Tabela dla Krajów
CREATE TABLE IF NOT EXISTS countries (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE,
    continent_id BIGINT,
    FOREIGN KEY (continent_id) REFERENCES continents(id)
) ENGINE=InnoDB;

-- Tabela dla Miast
CREATE TABLE IF NOT EXISTS cities (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE,
    country_id BIGINT,
    FOREIGN KEY (country_id) REFERENCES countries(id)
) ENGINE=InnoDB;

-- Tabela dla Użytkowników
CREATE TABLE IF NOT EXISTS users (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    phone VARCHAR(255),
    enabled TINYINT NOT NULL
) ENGINE=InnoDB;

-- Tabela dla ról użytkowników, którą wymaga Hibernate.
CREATE TABLE IF NOT EXISTS user_roles (
    user_id BIGINT NOT NULL,
    roles VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB;

-- Tabela dla Ofert
CREATE TABLE IF NOT EXISTS offers (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    price DECIMAL(12, 2) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'AVAILABLE',
    continent_id BIGINT NOT NULL,
    country_id BIGINT,
    city_id BIGINT,
    FOREIGN KEY (continent_id) REFERENCES continents(id),
    FOREIGN KEY (country_id) REFERENCES countries(id),
    FOREIGN KEY (city_id) REFERENCES cities(id)
) ENGINE=InnoDB;

-- Tabela dla Rezerwacji
CREATE TABLE IF NOT EXISTS reservations (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    offer_id BIGINT NOT NULL,
    reservation_date DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (offer_id) REFERENCES offers(id)
) ENGINE=InnoDB;