-- Tworzenie bazy danych
CREATE DATABASE IF NOT EXISTS onewaytickettravel;
USE onewaytickettravel;

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

-- Tabela dla Ofert
CREATE TABLE IF NOT EXISTS offers (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    price DECIMAL(12, 2) NOT NULL,
    continent_id BIGINT NOT NULL,
    country_id BIGINT,
    FOREIGN KEY (continent_id) REFERENCES continents(id),
    FOREIGN KEY (country_id) REFERENCES countries(id)
) ENGINE=InnoDB;
