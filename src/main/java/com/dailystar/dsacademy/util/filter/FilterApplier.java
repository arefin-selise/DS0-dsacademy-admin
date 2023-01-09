package com.dailystar.dsacademy.util.filter;

import com.dailystar.dsacademy.util.enums.SearchFieldType;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class FilterApplier extends QueryApplier
{
    public  static Query buildQuery(SearchFilter searchFilter, Class<?> searchClass) {
        Query query = new Query();
        FilterApplier.applyRestApiQueries(query, searchFilter, searchClass.getCanonicalName());
        return query;
    }

    public static void applyRestApiQueries(Query query, SearchFilter restApiQueries, String className) {
        applySortQuery(query, restApiQueries);
        applySelectQuery(query, restApiQueries);
        applySearchQuery(query, restApiQueries, className);
        applyPageQuery(query, restApiQueries);
        applyPageSizeQuery(query, restApiQueries);
    }

    public static void applyPageQuery(Query query, SearchFilter restApiQueries) {
        try {
            if (restApiQueries.getPage() != null) {
                Integer pageQueryParam = restApiQueries.getPage();
                long skip = pageQueryParam.longValue();
                if (skip < 0) {
                    throw new NumberFormatException();
                }
                query.skip(skip);
            }
        } catch (NumberFormatException pageParamExc) {
            throw new RuntimeException("Page param must be greater than or equal to 0");
        }
    }

    public static void applyPageSizeQuery(Query query, SearchFilter restApiQueries) {
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
        } catch (NumberFormatException pageParamExc) {
            throw new RuntimeException("PageSize param must greater than or equal to 0");
        }
    }

    public static void applySearchQuery(Query query, SearchFilter restApiQueries, String className) {
        if (restApiQueries.getQuery() == null) return;
        if (restApiQueries.getQuery().isEmpty()) return;

        try {
            Map<String, String> typeMap = getClassFieldNameTypeMap(className, restApiQueries.getQuery().stream().map(QueryFilterDetails::getName).collect(Collectors.toSet()));
            List<QueryFilterDetails> queryFilterDetails = restApiQueries.getQuery();
            for (QueryFilterDetails queryFilter : queryFilterDetails) {
                if (typeMap.containsKey(queryFilter.getName())) {
                    String typeName = typeMap.get(queryFilter.getName());
                    queryFilter.setType(typeName);
                    switch (SearchFieldType.valueOf(typeName)) {
                        case LOCALDATE -> applyLocalDateQuery(query, queryFilter);
                        case LOCALDATETIME -> applyLocalDateTimeQuery(query, queryFilter);
                        case DOUBLE,INTEGER -> applyNumberQuery(query, queryFilter);
                        default -> applyQuery(query, queryFilter);
                    }
                }
            }
        } catch (Exception c) {
            if (c instanceof ClassNotFoundException) {
                throw new RuntimeException(String.format("%s class not found for query", className));
            } else {
                throw new RuntimeException("Query could not be executed");
            }
        }

    }

    public static void applySelectQuery(Query query, SearchFilter restApiQueries) {
        if (restApiQueries.getSelect() != null) {
            List<String> selectedFields = restApiQueries.getSelect();
            for (String selectedField : selectedFields) {
                query.fields().include(selectedField);
            }
        }
    }

    public static void applySortQuery(Query query, SearchFilter restApiQueries) {
        try {
            if (restApiQueries.getSort() != null) {
                String sortQueryParam = restApiQueries.getSort();
                Sort.Direction sortDir =
                        sortQueryParam.charAt(0) == '-' ? Sort.Direction.DESC : Sort.Direction.ASC;
                String sortBy = sortDir.equals(Sort.Direction.DESC) ? sortQueryParam.substring(1)
                        : sortQueryParam;

                query.with(Sort.by(sortDir, sortBy));
            }
        } catch (IndexOutOfBoundsException subStringException) {
            throw new RuntimeException();
        }
    }


    public static Map<String, String> getClassFieldNameTypeMap(String className, Set<String> fieldsNames) throws ClassNotFoundException {
        Class<?> cls = Class.forName(className);
        Field[] allFields = cls.getDeclaredFields();
        Map<String, String> fieldNameToTypeMap = new HashMap<>();
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
