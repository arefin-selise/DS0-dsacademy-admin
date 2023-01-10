package com.dailystar.dsacademy.service;

import com.dailystar.dsacademy.dto.request.CategoryRequestDto;
import com.dailystar.dsacademy.model.Category;

import java.util.List;

public interface CategoryService
{
    Category createCategory(final CategoryRequestDto request);
    Category updateCategory(final CategoryRequestDto request);
    Category fetchCategoryByCategoryId(final String categoryId);
    List<Category> fetchAllCategories();
}
