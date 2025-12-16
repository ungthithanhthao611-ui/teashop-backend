package com.example.demo.service;

import com.example.demo.model.Category;
import java.util.List;

public interface CategoryService {

    void create(Category category);

    List<Category> getAll();

    void delete(Long id);
}
