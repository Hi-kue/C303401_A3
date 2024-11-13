package com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private String message;
    private HttpStatus status;
    private T data;

    //region Timestamps
    private Timestamp timestamp;
    //endregion
}
