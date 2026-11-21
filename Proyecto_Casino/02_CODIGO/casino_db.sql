CREATE DATABASE IF NOT EXISTS casino_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE casino_db;

CREATE TABLE IF NOT EXISTS jugadores (
  id_jugador INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  saldo DECIMAL(12,2) NOT NULL DEFAULT 0.00
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS juegos (
  id_juego INT PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL
) ENGINE=InnoDB;

INSERT INTO juegos (id_juego, nombre) VALUES
(1, 'Dado'), (2, 'Ruleta'), (3, 'Tragaperras')
ON DUPLICATE KEY UPDATE nombre=VALUES(nombre);

CREATE TABLE IF NOT EXISTS historial_apuestas (
  id_apuesta INT AUTO_INCREMENT PRIMARY KEY,
  id_jugador INT NOT NULL,
  id_juego INT NOT NULL,
  monto_apostado DECIMAL(12,2) NOT NULL,
  resultado VARCHAR(20) NOT NULL,
  ganancia DECIMAL(12,2) NOT NULL DEFAULT 0.00,
  fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (id_jugador) REFERENCES jugadores(id_jugador),
  FOREIGN KEY (id_juego) REFERENCES juegos(id_juego)
) ENGINE=InnoDB;
