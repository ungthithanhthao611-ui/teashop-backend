

// package com.example.demo.controller;

// import com.example.demo.model.Product;
// import com.example.demo.service.ProductService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/products")
// @CrossOrigin(origins = "http://localhost:5173")
// public class ProductController {

//     private final ProductService service;

//     public ProductController(ProductService service) {
//         this.service = service;
//     }

//     // ================= CREATE =================
//     @PostMapping
//     public Product create(@RequestBody Product product) {
//         service.create(product);
//         return product;
//     }

//     // ================= READ ALL =================
//     @GetMapping
//     public List<Product> getAll() {
//         return service.getAll();
//     }

//     // ================= READ BY ID =================
//     @GetMapping("/{id}")
//     public Product getById(@PathVariable Long id) {
//         return service.getById(id);
//     }

//     // ================= UPDATE =================
//     @PutMapping("/{id}")
//     public void update(@PathVariable Long id,
//                        @RequestBody Product product) {
//         service.update(id, product);
//     }

//     // ================= DELETE =================
//     @DeleteMapping("/{id}")
//     public void delete(@PathVariable Long id) {
//         service.delete(id);
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
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    @Qualifier("pgJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Map<String, Object>> getAllProducts() {
        return jdbcTemplate.queryForList(
            """
            SELECT id, title, price, description, photo, category_id
            FROM product
            ORDER BY id
            """
        );
    }

    @GetMapping("/category/{categoryId}")
    public List<Map<String, Object>> getProductsByCategory(
            @PathVariable Long categoryId) {

        return jdbcTemplate.queryForList(
            """
            SELECT id, title, price, description, photo, category_id
            FROM product
            WHERE category_id = ?
            ORDER BY id
            """,
            categoryId
        );
    }
}
