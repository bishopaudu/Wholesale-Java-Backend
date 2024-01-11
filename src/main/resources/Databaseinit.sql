CREATE TABLE IF NOT EXISTS address (
    id INTEGER PRIMARY KEY,
    address_line1 VARCHAR(255),
    address_line2 VARCHAR(255),
    address_line3 VARCHAR(255),
    country VARCHAR(255)
    postal_code VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS customer (
    id INTEGER PRIMARY KEY,
    business_name VARCHAR(255),
    phone VARCHAR(20),
    address_id INTEGER,
    FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE IF NOT EXISTS user (
    id INTEGER PRIMARY KEY,
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS product (
    id INTEGER PRIMARY KEY,
    sku VARCHAR(255),
    description VARCHAR(255)
    category VARCHAR(255)
    price DECIMAL(10,2),

);