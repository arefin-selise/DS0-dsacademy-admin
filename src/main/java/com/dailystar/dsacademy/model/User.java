package com.dailystar.dsacademy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Document(collection = "Users")
public class User
{
    @Id
    private String id;
    @Field(name = "FirstName")
    private String firstName;
    @Field(name = "LastName")
    private String lastName;
    @Field(name = "DateOfBirth")
    private LocalDate dateOfBirth;
    @Field(name = "Gender")
    private String gender;
    @Field(name = "Occupation")
    private String occupation;
    @Field(name = "Organization")
    private String organization;
    @Field(name = "Department")
    private String department;
    @Field(name = "Email")
    private String email;
    @Field(name = "Password")
    private String password;
    @Field(name = "Mobile")
    private String mobile;
    @Field(name = "Locale")
    private String locale;
    @Field(name = "Notes")
    private String notes;
    @Field(name = "Body")
    private String body;
    @Field(name = "ProfileImage")
    private String profileImage;
    @Field(name = "Attachments")
    private String attachments;
    @Field(name = "TimeZone")
    private String timeZone;
    @Field(name = "Address")
    private String address;
    @Field(name = "LastLogInTime")
    private LocalDateTime lastLogInTime;
    @Field(name = "Roles")
    private Set<String> roles = new HashSet<>();
    @Field(name = "isActive")
    private boolean isActive;
    @Field(name = "LogInCount")
    private int loginCount;
    @Field(name = "Status")
    private String status;
    @Field(name = "Language")
    private String language;
    @Field(name="Badges")
    private List<String> badges;
    @Field(name="CompletedCourses")
    private List<String> completedCourses;
    @Field(name="CareerHistory")
    private List<String> careerHistory;
    @Field(name="AcademicHistory")
    private List<String> academicHistory;
    @Field(name = "IsVerified")
    private boolean isVerified;
    @Field(name = "LinkedInProfile")
    private String linkedInProfile;
    @Field(name = "Created")
    private String created;
    @Field(name = "Modified")
    private String modified;

    public void addRole(final String role) {
        this.roles.add(role);
    }

    public void addRoles(final List<String> roles) {
        this.roles.addAll(roles);
    }

    public int incrementLoginCount() {
        return this.loginCount + 1;
    }
}
