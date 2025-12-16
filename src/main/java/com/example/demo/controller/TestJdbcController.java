package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestJdbcController {

    @Autowired
    @Qualifier("pgJdbcTemplate") // 👈 CHỈ RÕ DÙNG POSTGRES
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/db-test")
    public String testDb() {
        return jdbcTemplate.queryForObject(
                "SELECT 'DB CONNECT OK'",
                String.class
        );
    }
}
