package com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BloodBank {
    private Long id;

    @NotBlank(message = "Blood bank name is a required field.")
    private String bloodBankName;

    @NotBlank(message = "Address is a required field.")
    private String address;

    @NotBlank(message = "City is a required field.")
    private String city;

    @NotBlank(message = "Phone Number is a required field.")
    private String phoneNumber;

    @NotBlank(message = "Email is a required field.")
    private String email;

    //region Timestamps
    private Date createdAt;
    private Date updatedAt;
    //endregion
}
