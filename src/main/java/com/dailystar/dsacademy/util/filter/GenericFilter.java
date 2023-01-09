package com.dailystar.dsacademy.util.filter;

import org.springframework.data.mongodb.core.query.Query;

public interface GenericFilter
{
    final int DEFAULT_PAGE_SIZE = 20;

    default Query buildQuery(final SearchFilter searchFilter, Class<?> searchClass)
    {
        Query query = new Query();
        FilterApplier.applyRestApiQueries(query, searchFilter, searchClass.getCanonicalName());
        return query;
    }
}
