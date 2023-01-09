package com.dailystar.dsacademy.repository;

import com.dailystar.dsacademy.util.filter.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface CustomSearchRepository<T>
{
    Page<T> search(Query query, Pageable pageable, Class<T> tClass);
    List<T> search(Query query, Class<T> tClass);
    List<T> search(SearchFilter searchFilter, Class<T> tClass);
}
