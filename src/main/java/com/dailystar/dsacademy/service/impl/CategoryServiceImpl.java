package com.dailystar.dsacademy.service.impl;

import com.dailystar.dsacademy.dto.request.CategoryRequestDto;
import com.dailystar.dsacademy.model.Category;
import com.dailystar.dsacademy.repository.CategoryRepository;
import com.dailystar.dsacademy.service.CategoryService;
import com.dailystar.dsacademy.util.filter.FilterApplier;
import com.dailystar.dsacademy.util.mapper.ModelMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@Lazy
public class CategoryServiceImpl extends FilterApplier implements CategoryService
{
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(final @Lazy CategoryRepository categoryRepository, final ModelMapper modelMapper)
    {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Category createCategory(final CategoryRequestDto request)
    {
        final Category category = modelMapper.mapCategoryRequestDtoToCategory(request);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(final CategoryRequestDto request)
    {
        final Category category = modelMapper.mapCategoryRequestDtoToCategory(request);
        category.setId(request.getCategoryId());
        return categoryRepository.save(category);
    }

    @Override
    public Category fetchCategoryByCategoryId(final String categoryId)
    {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category details not found"));
    }

    @Override
    public List<Category> fetchAllCategories()
    {
        return categoryRepository.findAllCategories().stream().toList();
    }
}
