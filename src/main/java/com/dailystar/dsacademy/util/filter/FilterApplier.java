package com.dailystar.dsacademy.util.filter;

import com.dailystar.dsacademy.util.enums.SearchFieldType;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class FilterApplier extends QueryApplier
{
    public  static Query buildQuery(final SearchFilter searchFilter, final Class<?> searchClass) {
        final Query query = new Query();
        FilterApplier.applyRestApiQueries(query, searchFilter, searchClass.getCanonicalName());
        return query;
    }

    public static void applyRestApiQueries(final Query query, final SearchFilter restApiQueries, final String className) {
        applySortQuery(query, restApiQueries);
        applySelectQuery(query, restApiQueries);
        applySearchQuery(query, restApiQueries, className);
        applyPageQuery(query, restApiQueries);
        applyPageSizeQuery(query, restApiQueries);
    }

    public static void applyPageQuery(final Query query, final SearchFilter restApiQueries) {
        try {
            if (restApiQueries.getPage() != null) {
                final Integer pageQueryParam = restApiQueries.getPage();
                final long skip = pageQueryParam.longValue();
                if (skip < 0) {
                    throw new NumberFormatException();
                }
                query.skip(skip);
            }
        } catch (final NumberFormatException pageParamExc) {
            throw new RuntimeException("Page param must be greater than or equal to 0");
        }
    }

    public static void applyPageSizeQuery(final Query query, final SearchFilter restApiQueries) {
        try {
            if (restApiQueries.getPageSize() != null) {
                int limit = restApiQueries.getPageSize();
                if (limit < 0) {
                    throw new NumberFormatException();
                }
                if (limit > 50) {
                    limit = 50;
                }
                query.limit(limit);
            } else {
                query.limit(20);
            }
        } catch (final NumberFormatException pageParamExc) {
            throw new RuntimeException("PageSize param must greater than or equal to 0");
        }
    }

    public static void applySearchQuery(final Query query, final SearchFilter restApiQueries, final String className) {
        if (restApiQueries.getQuery() == null) return;
        if (restApiQueries.getQuery().isEmpty()) return;

        try {
            final Map<String, String> typeMap = getClassFieldNameTypeMap(className, restApiQueries.getQuery().stream().map(QueryFilterDetails::getName).collect(Collectors.toSet()));
            final List<QueryFilterDetails> queryFilterDetails = restApiQueries.getQuery();
            for (QueryFilterDetails queryFilter : queryFilterDetails) {
                if (typeMap.containsKey(queryFilter.getName())) {
                    final String typeName = typeMap.get(queryFilter.getName());
                    queryFilter.setType(typeName);
                    switch (SearchFieldType.valueOf(typeName)) {
                        case LOCALDATE -> applyLocalDateQuery(query, queryFilter);
                        case LOCALDATETIME -> applyLocalDateTimeQuery(query, queryFilter);
                        case DOUBLE,INTEGER -> applyNumberQuery(query, queryFilter);
                        default -> applyQuery(query, queryFilter);
                    }
                }
            }
        } catch (final Exception c) {
            if (c instanceof ClassNotFoundException) {
                throw new RuntimeException(String.format("%s class not found for query", className));
            } else {
                throw new RuntimeException("Query could not be executed");
            }
        }

    }

    public static void applySelectQuery(final Query query, final SearchFilter restApiQueries) {
        if (restApiQueries.getSelect() != null) {
            final List<String> selectedFields = restApiQueries.getSelect();
            for (String selectedField : selectedFields) {
                query.fields().include(selectedField);
            }
        }
    }

    public static void applySortQuery(final Query query, final SearchFilter restApiQueries) {
        try {
            if (restApiQueries.getSort() != null) {
                final String sortQueryParam = restApiQueries.getSort();
                Sort.Direction sortDir =
                        sortQueryParam.charAt(0) == '-' ? Sort.Direction.DESC : Sort.Direction.ASC;
                final String sortBy = sortDir.equals(Sort.Direction.DESC) ? sortQueryParam.substring(1)
                        : sortQueryParam;

                query.with(Sort.by(sortDir, sortBy));
            }
        } catch (final IndexOutOfBoundsException subStringException) {
            throw new RuntimeException();
        }
    }


    public static Map<String, String> getClassFieldNameTypeMap(final String className, final Set<String> fieldsNames) throws ClassNotFoundException {
        final Class<?> cls = Class.forName(className);
        final Field[] allFields = cls.getDeclaredFields();
        final Map<String, String> fieldNameToTypeMap = new HashMap<>();
        Arrays.stream(allFields)
                .filter(data-> fieldsNames.contains(data.getName()))
                .forEach(field -> {
                    String type;
                    if (field.getType().isEnum()) {
                        type = SearchFieldType.STRING.getCode();
                    } else {
                        type = field.getType().getSimpleName();
                    }
                    if (type != null) {
                        fieldNameToTypeMap.put(field.getName(), type.toUpperCase());
                    }
                });

        return fieldNameToTypeMap;
    }
}
