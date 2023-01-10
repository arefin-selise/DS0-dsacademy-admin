package com.dailystar.dsacademy.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserResponseDto implements Serializable
{
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String status;
}
