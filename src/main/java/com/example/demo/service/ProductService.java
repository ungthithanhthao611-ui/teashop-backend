
// package com.example.demo.service;

// import com.example.demo.model.Product;

// import java.util.List;

// public interface ProductService {

//     // Create
//     void create(Product product);

//     // Read
//     List<Product> getAll();
//     Product getById(Long id);

//     // Update
//     void update(Long id, Product product);

//     // Delete
//     void delete(Long id);
// }

package com.example.demo.service;

import com.example.demo.model.Product;
import java.util.List;

public interface ProductService {

    void create(Product product);

    List<Product> getAll();

    Product getById(Long id);

    void update(Long id, Product product);

    void delete(Long id);
}
