package com.project.ordersystem.SecurityConfig;

import com.project.ordersystem.SecurityConfig.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class AuthController {

    private final JdbcTemplate jdbcTemplate;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(JdbcTemplate jdbcTemplate, BCryptPasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        int result = jdbcTemplate.update(sql, user.getUsername(), encodedPassword, user.getEmail());

        if (result == 1) {
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to register user", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginUser) {
        // Implement login logic here if needed
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();

        // Retrieve user details from the database
        String sql = "SELECT * FROM users WHERE username = ?";
        User storedUser = jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserRowMapper());

        if (storedUser != null && passwordEncoder.matches(password, storedUser.getPassword())) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }    }
}
