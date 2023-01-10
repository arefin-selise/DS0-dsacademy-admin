package com.dailystar.dsacademy.repository;

import com.dailystar.dsacademy.model.Category;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Lazy
public interface CategoryRepository extends MongoRepository<Category, String>, CustomSearchRepository<Category>
{
    Optional<Category> findById(final String categoryId);
    List<Category> findAllByIdIsNotNull();
}
