package com.dailystar.dsacademy.repository.impl;

import com.dailystar.dsacademy.repository.CustomSearchRepository;
import com.dailystar.dsacademy.util.filter.GenericFilter;
import com.dailystar.dsacademy.util.filter.SearchFilter;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomSearchRepositoryImpl<T> implements CustomSearchRepository<T>, GenericFilter
{
    private final MongoTemplate mongoTemplate;

    public CustomSearchRepositoryImpl(@Lazy MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<T> search(Query query, Pageable pageable, Class<T> aClass) {
        List<T> list = mongoTemplate.find(query.with(pageable), aClass);
        long count = mongoTemplate.count(query.skip(0).limit(0), aClass);
        Page<T> resultsWithPage = PageableExecutionUtils.getPage(list, pageable, () -> count);
        return resultsWithPage;
    }

    @Override
    public List<T> search(Query query, Class<T> aClass) {
        return mongoTemplate.find(query, aClass);
    }

    @Override
    public List<T> search(SearchFilter searchFilter, Class<T> tClass) {
        Query query = buildQuery(searchFilter, tClass);
        return mongoTemplate.find(query, tClass);
    }
}
