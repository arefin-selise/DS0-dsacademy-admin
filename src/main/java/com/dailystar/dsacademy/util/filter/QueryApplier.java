package com.dailystar.dsacademy.util.filter;

import com.dailystar.dsacademy.util.enums.SearchFieldType;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class QueryApplier
{
    public static void applyLocalDateQuery(Query query, QueryFilterDetails queryFilterDetails) {
        Criteria criteria = new Criteria(queryFilterDetails.getName());

        if (queryFilterDetails.getRange() != null && !queryFilterDetails.getRange().isEmpty()) {
            List<String> values = (List<String>) queryFilterDetails.getRange();

            if (values.get(0) != null) {
                LocalDate localDate1 = LocalDate.parse(values.get(0));
                criteria.gt(localDate1);
            }
            if (values.get(1) != null) {
                LocalDate localDate2 = LocalDate.parse(values.get(1));
                criteria.lte(localDate2);
            }


        } else {
            LocalDate localDate = LocalDate.parse((String) queryFilterDetails.getValue());
            criteria.is(localDate);
        }
        query.addCriteria(criteria);
    }

    public static void applyLocalDateTimeQuery(Query query, QueryFilterDetails queryFilterDetails) {
        Criteria criteria = new Criteria(queryFilterDetails.getName());
        if (queryFilterDetails.getRange() != null && !queryFilterDetails.getRange().isEmpty()) {
            List<String> values = (List<String>) queryFilterDetails.getRange();

            if (values.get(0) != null) {
                LocalDate localDate1 = LocalDate.parse(values.get(0));
                LocalDateTime localDateTime = LocalDateTime.of(localDate1.getYear(), localDate1.getMonth(), localDate1.getDayOfMonth(), 0, 0, 0);
                criteria.gt(localDateTime);
            }
            if (values.get(1) != null) {
                LocalDate localDate2 = LocalDate.parse(values.get(1));
                LocalDateTime localDateTime = LocalDateTime.of(localDate2.getYear(), localDate2.getMonth(), localDate2.getDayOfMonth(), 0, 0, 0);
                criteria.lte(localDateTime);
            }
        } else {
            LocalDate localDate2 = LocalDate.parse((String) queryFilterDetails.getValue());
            LocalDateTime localDateTime = LocalDateTime.of(localDate2.getYear(), localDate2.getMonth(), localDate2.getDayOfMonth(), 0, 0, 0);
            criteria.gte(localDateTime).lte(localDateTime.plusDays(1));
        }
        query.addCriteria(criteria);

    }

    public static void applyNumberQuery(Query query, QueryFilterDetails queryFilterDetails) {
        Criteria criteria1 = new Criteria(queryFilterDetails.getName());
        if (queryFilterDetails.getRange() != null && !queryFilterDetails.getRange().isEmpty()) {
            if (queryFilterDetails.getRange().get(0) != null) {
                criteria1.gt(queryFilterDetails.getRange().get(0));
            }
            if (queryFilterDetails.getRange().size() > 1 && queryFilterDetails.getRange().get(1) != null) {
                criteria1.lte(queryFilterDetails.getRange().get(1));
            }

        } else {
//            criteria1.type(List.of(JsonSchemaObject.Type.INT_32,JsonSchemaObject.Type.INT_64,JsonSchemaObject.Type.DOUBLE));
            if (queryFilterDetails.getValue() instanceof String) {
                if (queryFilterDetails.getType().equals(SearchFieldType.DOUBLE.toString())) {
                    criteria1.is(Double.valueOf((String) queryFilterDetails.getValue()));
                }
                if (queryFilterDetails.getType().equals(SearchFieldType.INTEGER.toString())) {
                    criteria1.is(Integer.valueOf((String) queryFilterDetails.getValue()));
                }
            }

        }
        query.addCriteria(criteria1);
    }


    public static void applyQuery(Query query, QueryFilterDetails queryFilterDetails) {
        Criteria criteria1 = new Criteria(queryFilterDetails.getName());
        if (queryFilterDetails.getRange() != null && !queryFilterDetails.getRange().isEmpty()) {

            if (queryFilterDetails.getRange().get(0) != null) {
                criteria1.gt(queryFilterDetails.getRange().get(0));
            }
            if (queryFilterDetails.getRange().size() > 1 && queryFilterDetails.getRange().get(1) != null) {
                criteria1.lte(queryFilterDetails.getRange().get(1));
            }

        } else {
            criteria1.is(queryFilterDetails.getValue());
        }
        query.addCriteria(criteria1);
    }
}
