package com.dailystar.dsacademy.controller;

import com.dailystar.dsacademy.dto.request.CategoryRequestDto;
import com.dailystar.dsacademy.model.Category;
import com.dailystar.dsacademy.service.CategoryService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController
{
    private final CategoryService categoryService;

    @Lazy
    public CategoryController(final CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCategory(@RequestBody CategoryRequestDto request)
    {
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

    @PostMapping("/edit")
    public ResponseEntity<Object> editCourse(@RequestBody CategoryRequestDto request)
    {
        return ResponseEntity.ok(categoryService.updateCategory(request));
    }

    @GetMapping( "/view/{categoryId}")
    public ResponseEntity<Category> viewCategoryById(@PathVariable String categoryId)
    {
        return ResponseEntity.ok(categoryService.fetchCategoryByCategoryId(categoryId));
    }

    @GetMapping("/view/categories")
    public ResponseEntity<List<Category>> viewCategoryList()
    {
        return ResponseEntity.ok(categoryService.fetchAllCategories());
    }
}
