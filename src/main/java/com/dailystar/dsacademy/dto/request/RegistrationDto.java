package com.dailystar.dsacademy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private int gender;
    private String email;
    private String role;
}
