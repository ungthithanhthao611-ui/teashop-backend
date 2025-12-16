// package com.example.demo.controller;

// import com.example.demo.model.Category;
// import com.example.demo.service.CategoryService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/categories")
// @CrossOrigin(origins = "http://localhost:5173")
// public class CategoryController {

//     private final CategoryService service;

//     public CategoryController(CategoryService service) {
//         this.service = service;
//     }

//     // CREATE
//     @PostMapping
//     public String create(@RequestBody Category category) {
//         service.create(category);
//         return "Create category successfully";
//     }

//     // READ ALL
//     @GetMapping
//     public List<Category> getAll() {
//         return service.getAll();
//     }

//     // DELETE
//     @DeleteMapping("/{id}")
//     public String delete(@PathVariable Long id) {
//         service.delete(id);
//         return "Delete category successfully";
//     }
// }
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    @Qualifier("pgJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Map<String, Object>> getAllCategories() {
        return jdbcTemplate.queryForList(
            "SELECT id, name FROM category ORDER BY id"
        );
    }
}
