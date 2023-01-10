package com.dailystar.dsacademy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Document(collection = "Categories")
public class Category
{
    @Id
    private String id;
    @Field(name ="Name")
    private String name;
    @Field(name ="Description")
    private String description;
    @Field(name ="Body")
    private String body;
    @Field(name ="Picture")
    private String picture;
    @Field(name ="Logo")
    private String logo;
    @Field(name ="CourseCount")
    private int courseCount;
    @Field(name ="Created")
    private LocalDateTime created;
    @Field(name ="Modified")
    private LocalDateTime modified;
}
