package com.dailystar.dsacademy.dto.response;

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
public class CategoryResponseDto implements Serializable
{
    private String categoryId;
    private String name;
    private String description;
    private String body;
    private String picture;
    private String logo;
    private int courseCount;
    private LocalDateTime created;
    private LocalDateTime modified;
}
