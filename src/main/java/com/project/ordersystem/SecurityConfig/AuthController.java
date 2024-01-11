package com.project.ordersystem.SecurityConfig;

import com.project.ordersystem.SecurityConfig.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    private final UserDAO userDAO;

    @Autowired
    public AuthController(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder,UserDAO userDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
        this.userDAO = userDAO;
    }

    @GetMapping("/testrepo")
    public String get(){
        return "its works";
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginUser) {

        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        String sql = "SELECT * FROM users WHERE username = ?";
        User storedUser = jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserRowMapper());

        if (storedUser != null && passwordEncoder.matches(password, storedUser.getPassword())) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }    }

    @PostMapping("/signup")
    public ResponseEntity<String> signupUser(@RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        int result = jdbcTemplate.update(sql, user.getUsername(), encodedPassword, user.getEmail());

        if (result == 1) {
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to register user", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getall")
    public List<User> getallusers(){
        return userDAO.getAllUsers();

    }
}
