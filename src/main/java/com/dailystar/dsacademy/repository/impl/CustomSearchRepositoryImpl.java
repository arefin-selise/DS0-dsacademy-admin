package com.dailystar.dsacademy.repository.impl;

import com.dailystar.dsacademy.repository.CustomSearchRepository;
import com.dailystar.dsacademy.util.filter.FilterApplier;
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
public class CustomSearchRepositoryImpl<T> extends FilterApplier implements CustomSearchRepository<T>
{
    private final MongoTemplate mongoTemplate;

    public CustomSearchRepositoryImpl(@Lazy MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<T> search(final Query query, final Pageable pageable, final Class<T> aClass) {
        final List<T> list = mongoTemplate.find(query.with(pageable), aClass);
        final long count = mongoTemplate.count(query.skip(0).limit(0), aClass);
        final Page<T> resultsWithPage = PageableExecutionUtils.getPage(list, pageable, () -> count);
        return resultsWithPage;
    }

    @Override
    public List<T> search(final Query query, final Class<T> aClass) {
        return mongoTemplate.find(query, aClass);
    }

    @Override
    public List<T> search(final SearchFilter searchFilter, final Class<T> tClass) {
        final Query query = buildQuery(searchFilter, tClass);
        return mongoTemplate.find(query, tClass);
    }
}
