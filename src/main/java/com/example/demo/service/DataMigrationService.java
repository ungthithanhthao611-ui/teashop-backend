package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataMigrationService {

    @Autowired
    @Qualifier("mysqlJdbcTemplate")
    private JdbcTemplate mysqlJdbc;

    @Autowired
    @Qualifier("pgJdbcTemplate")
    private JdbcTemplate pgJdbc;

    // ===============================
    // MIGRATE CATEGORY (OK)
    // ===============================
    public void migrateCategory() {
        List<Map<String, Object>> categories =
                mysqlJdbc.queryForList("SELECT id, name FROM category");

        for (Map<String, Object> c : categories) {
            pgJdbc.update(
                """
                INSERT INTO category (id, name)
                SELECT ?, ?
                WHERE NOT EXISTS (
                    SELECT 1 FROM category WHERE id = ?
                )
                """,
                c.get("id"),
                c.get("name"),
                c.get("id")
            );
        }
    }

    // ===============================
    // MIGRATE PRODUCT (FIX THEO MYSQL)
    // ===============================
    public void migrateProduct() {
        List<Map<String, Object>> products =
                mysqlJdbc.queryForList(
                    "SELECT id, title, price, description, photo, category_id FROM product"
                );

        for (Map<String, Object> p : products) {
            pgJdbc.update(
                """
                INSERT INTO product (id, title, price, description, photo, category_id)
                SELECT ?, ?, ?, ?, ?, ?
                WHERE NOT EXISTS (
                    SELECT 1 FROM product WHERE id = ?
                )
                """,
                p.get("id"),
                p.get("title"),
                p.get("price"),
                p.get("description"),
                p.get("photo"),
                p.get("category_id"),
                p.get("id")
            );
        }
    }

    // ===============================
    // MIGRATE ALL
    // ===============================
    public void migrateAll() {
        migrateCategory();
        migrateProduct();
    }
}
