package com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloodStock {
    private Long id;

    @NotBlank(message = "Blood group is a required field.")
    private String bloodGroup;

    @NotBlank(message = "Quantity is a required field.")
    @Min(value = 1, message = "Quantity must be greater than or equal to 1.")
    private Integer quantity;

    @NotBlank(message = "Expiry date is a required field.")
    private Date expiryDate; // NOTE: Expiry data just sounded better.

    @NotBlank(message = "Status is a required field.")
    private String status;

    //region Timestamps
    private Date createdAt;
    private Date updatedAt;
    //endregion
}
