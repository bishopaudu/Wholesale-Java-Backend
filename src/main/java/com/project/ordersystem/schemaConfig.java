package com.project.ordersystem;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class schemaConfig {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public schemaConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() {
        createTableIfNotExists("users", "id INTEGER PRIMARY KEY, username VARCHAR(255), email VARCHAR(255), password VARCHAR(255), name VARCHAR(255)");
        createTableIfNotExists("products", "id INTEGER PRIMARY KEY, sku VARCHAR(255),  description VARCHAR(255),category VARCHAR(255),price DECIMAL(10,2)");
    }

    private void createTableIfNotExists(String tableName, String columns) {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS " + tableName + " (" + columns + ")");
    }
}
