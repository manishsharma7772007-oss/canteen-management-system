CREATE DATABASE IF NOT EXISTS canteen_db;
USE canteen_db;

CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(100) NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    role ENUM('Admin', 'Student') NOT NULL
);

CREATE TABLE IF NOT EXISTS food_items (
    food_id INT NOT NULL PRIMARY KEY,
    food_name VARCHAR(150) NOT NULL,
    category ENUM('Snacks', 'Breakfast', 'Lunch', 'Beverages', 'Fast Food', 'Dessert') NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL DEFAULT 0
);
