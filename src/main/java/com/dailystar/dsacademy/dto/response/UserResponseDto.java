package com.dailystar.dsacademy.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserResponseDto implements Serializable
{
    private String userId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String occupation;
    private String organization;
    private String department;
    private String email;
    private String password;
    private String mobile;
    private String locale;
    private String notes;
    private String body;
    private String profileImage;
    private String attachments;
    private String timeZone;
    private String address;
    private LocalDateTime lastLogInTime;
    private Set<String> roles = new HashSet<>();
    private boolean isActive;
    private int loginCount;
    private String status;
    private String language;
    private List<String> badges;
    private List<String> completedCourses;
    private List<String> careerHistory;
    private List<String> academicHistory;
    private boolean isVerified;
    private String linkedInProfile;
    private LocalDateTime created;
    private LocalDateTime modified;
}
