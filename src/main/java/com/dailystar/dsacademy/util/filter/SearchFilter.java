package com.dailystar.dsacademy.util.filter;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class SearchFilter implements Serializable
{
    public List<QueryFilterDetails> query= new ArrayList<>();
    public Integer page;
    public Integer pageSize;
    private String sort;
    public List<String> select = new ArrayList<>();
}
