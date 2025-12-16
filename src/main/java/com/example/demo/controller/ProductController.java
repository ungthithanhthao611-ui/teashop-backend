package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Map<String, Object>> getAllProducts() {
        return jdbcTemplate.queryForList("""
            SELECT id, title, price, description, photo, category_id
            FROM product
            ORDER BY id
        """);
    }

    @GetMapping("/category/{categoryId}")
    public List<Map<String, Object>> getProductsByCategory(
            @PathVariable Long categoryId) {

        return jdbcTemplate.queryForList("""
            SELECT id, title, price, description, photo, category_id
            FROM product
            WHERE category_id = ?
            ORDER BY id
        """, categoryId);
    }
}
