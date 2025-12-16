// package com.example.demo.controller;

// import com.example.demo.model.Product;
// import com.example.demo.service.ProductService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/products")

// // ✅ CORS: cho phép frontend React (Vite) truy cập
// @CrossOrigin(
//         origins = "http://localhost:5173",
//         allowedHeaders = "*",
//         methods = {RequestMethod.GET, RequestMethod.POST,
//                    RequestMethod.PUT, RequestMethod.DELETE}
// )
// public class ProductController {

//     private final ProductService service;

//     public ProductController(ProductService service) {
//         this.service = service;
//     }

//     // ================= CREATE =================
//     @PostMapping
//     public String createProduct(@RequestBody Product product) {
//         service.create(product);
//         return "Create product successfully";
//     }

//     // ================= READ ALL =================
//     @GetMapping
//     public List<Product> getAllProducts() {
//         return service.getAll();
//     }

//     // ================= READ BY ID =================
//     @GetMapping("/{id}")
//     public Product getProductById(@PathVariable Long id) {
//         return service.getById(id);
//     }

//     // ================= UPDATE =================
//     @PutMapping("/{id}")
//     public String updateProduct(
//             @PathVariable Long id,
//             @RequestBody Product product
//     ) {
//         service.update(id, product);
//         return "Update product successfully";
//     }

//     // ================= DELETE =================
//     @DeleteMapping("/{id}")
//     public String deleteProduct(@PathVariable Long id) {
//         service.delete(id);
//         return "Delete product successfully";
//     }
// }


package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // ================= CREATE =================
    @PostMapping
    public Product create(@RequestBody Product product) {
        service.create(product);
        return product;
    }

    // ================= READ ALL =================
    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    // ================= READ BY ID =================
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody Product product) {
        service.update(id, product);
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
