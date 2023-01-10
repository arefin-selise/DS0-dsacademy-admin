package com.dailystar.dsacademy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CourseRequestDto implements Serializable
{
    private String courseId;
    private String enrollment;
    private int categoryId;
    private String name;
    private String description;
    private String picture;
    private String banner;
    private String contents;
    private String attachments;
    private LocalDateTime start;
    private LocalDateTime end;
    private int duration;
    private Double price;
    private Double salePrice;
    private Boolean certification;
    private LocalDateTime created;
    private LocalDateTime modified;
}
