package com.example.demo.controller;

import com.example.demo.service.DataMigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaType.TEXT_PLAIN_VALUE)
@Profile("local") // ✅ chỉ chạy local
public class MigrationController {

    @Autowired
    private DataMigrationService migrationService;

    @GetMapping("/migrate")
    public ResponseEntity<String> migrate() {
        try {
            migrationService.migrateAll();
            return ResponseEntity.ok("MIGRATION SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("MIGRATION FAILED: " + e.getMessage());
        }
    }
}
