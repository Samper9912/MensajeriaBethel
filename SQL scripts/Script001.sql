-- 1️⃣ Crear la base de datos
CREATE DATABASE IF NOT EXISTS MensajeriaBethel;
USE MensajeriaBethel;

-- 2️⃣ Tabla: Trabajador
CREATE TABLE Trabajador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    cargo VARCHAR(50),
    materia VARCHAR(50),
    esDocente BOOLEAN NOT NULL
);

-- 3️⃣ Tabla: Mensajes
CREATE TABLE Mensajes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombreRemitente VARCHAR(100) NOT NULL,
    emailDestinatario VARCHAR(100) NOT NULL,
    emailRemitente VARCHAR(100) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 4️⃣ Tabla: Admin
CREATE TABLE Admin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL
);

