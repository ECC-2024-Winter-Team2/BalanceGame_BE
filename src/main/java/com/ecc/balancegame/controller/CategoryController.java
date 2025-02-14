package com.ecc.balancegame.controller;

import com.ecc.balancegame.domain.Category;
import com.ecc.balancegame.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/api/categories")
    public List<Category> getCategories(){
        return categoryService.getAllCategories();
    }
}
