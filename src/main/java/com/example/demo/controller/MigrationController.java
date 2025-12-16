package com.example.demo.controller;

import com.example.demo.service.DataMigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.TEXT_PLAIN_VALUE)
public class MigrationController {

    @Autowired
    private DataMigrationService migrationService;

    @GetMapping("/migrate")
    public ResponseEntity<String> migrate() {
        try {
            migrationService.migrateAll();
            return ResponseEntity.ok("MIGRATION SUCCESS");
        } catch (Exception e) {
            e.printStackTrace(); // log ra console
            return ResponseEntity
                    .internalServerError()
                    .body("MIGRATION FAILED: " + e.getMessage());
        }
    }
}
