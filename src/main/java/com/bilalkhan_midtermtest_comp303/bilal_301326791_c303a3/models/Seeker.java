package com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seeker {
    private Long id;

    @NotBlank(message = "First name is a required field.")
    private String firstName;

    @NotBlank(message = "Last name is a required field.")
    private String lastName;

    @Range(min = 18, max = 99, message = "Age must be between 18 and 99.")
    private Integer age;

    @NotBlank(message = "Blood group is a required field.")
    private String bloodGroup;

    @NotBlank(message = "City is a required field.")
    private String city;

    @NotBlank(message = "Phone number is a required field.")
    private String phoneNumber;

    //region Timestamps
    private Timestamp createdAt;
    private Timestamp updatedAt;
    //endregion
}
