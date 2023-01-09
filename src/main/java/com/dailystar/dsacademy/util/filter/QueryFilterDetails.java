package com.dailystar.dsacademy.util.filter;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class QueryFilterDetails implements Serializable
{
    public String type;
    public Object value;
    public List<?> range;
    public String name;
}
