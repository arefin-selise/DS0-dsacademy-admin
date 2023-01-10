package com.dailystar.dsacademy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Document(collection = "Courses")
public class Course
{
    @Id
    private String id;
    @Field(name ="Enrollment")
    private String enrollment;
    @Field(name ="CategoryId")
    private UUID categoryId;
    @Field(name ="Name")
    private String name;
    @Field(name ="Description")
    private String description;
    @Field(name ="Picture")
    private String picture;
    @Field(name ="Banner")
    private String banner;
    @Field(name ="Contents")
    private String contents;
    @Field(name ="Attachments")
    private String attachments;
    @Field(name ="Start")
    private LocalDateTime start;
    @Field(name ="End")
    private LocalDateTime end;
    @Field(name ="Duration")
    private int duration;
    @Field(name ="Price")
    private Double price;
    @Field(name ="SalePrice")
    private Double salePrice;
    @Field(name ="LearnerCount")
    private int learnerCount;
    @Field(name ="Certification")
    private Boolean certification;
    @Field(name ="Created")
    private LocalDateTime created;
    @Field(name ="Modified")
    private LocalDateTime modified;
}
