package com.project.ordersystem.SecurityConfig;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    // Other methods for CRUD operations (create, update, delete)
}


