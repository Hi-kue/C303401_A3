package com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.api.controllers.advice;

import com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.exceptions.SeekerNotFoundException;
import com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.models.Seeker;
import com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.utils.ApiResponse;
import jakarta.validation.constraints.Null;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = SeekerNotFoundException.class)
    public ResponseEntity<ApiResponse> handleSeekerNotFoundException(SeekerNotFoundException e) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        ApiResponse apiResponse = ApiResponse
                .builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .data(null)
                .timestamp(currentTime)
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(apiResponse);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception e) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        ApiResponse apiResponse = ApiResponse
                .builder()
                .message(e.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .data(null)
                .timestamp(currentTime)
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(apiResponse);
    }
}
