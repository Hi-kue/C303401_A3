package com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.api.controllers;

import com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.models.BloodStock;
import com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.service.BloodStockService;
import com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("api/v1/blood-stocks")
public class BloodStockController {
    private final BloodStockService bloodStockService;

    @Autowired
    public BloodStockController(BloodStockService bloodStockService) {
        this.bloodStockService = bloodStockService;
    }

    /**
     * GET api/v1/blood-stocks
     * @return A list of all the blood stocks wrapped around an ApiResponse
     */
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<List<BloodStock>>> getBloodStocks() {
         return ResponseEntity
                 .status(HttpStatus.OK)
                 .header("Content-Type", "application/json")
                 .body(
                         ApiResponse.<List<BloodStock>>builder()
                                 .message("All blood stocks retrieved successfully.")
                                 .status(HttpStatus.OK)
                                 .data(bloodStockService.getAllBloodStocks())
                                 .timestamp(new Timestamp(System.currentTimeMillis()))
                                 .build()
                 );
    }

    /**
     * GET api/v1/blood-stocks/{id}
     * @param id The id of the blood stock you are looking to retrieve from the HashMap
     * @return The blood stock wrapped around an ApiResponse
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<ApiResponse<BloodStock>> getBloodStockById(@PathVariable(value = "id") Long id) {
        BloodStock bloodStock = bloodStockService.getBloodStockById(id);

        if (bloodStock == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            ApiResponse.<BloodStock>builder()
                                    .message(String.format("Blood stock with id %d not found", id))
                                    .status(HttpStatus.NOT_FOUND)
                                    .data(null)
                                    .timestamp(new Timestamp(System.currentTimeMillis()))
                                    .build()
                    );
        }

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(
                        ApiResponse.<BloodStock>builder()
                                .message(String.format("Blood stock with id %d found", id))
                                .status(HttpStatus.FOUND)
                                .data(bloodStock)
                                .timestamp(new Timestamp(System.currentTimeMillis()))
                                .build()
                );
    }

    /**
     * POST api/v1/blood-stocks
     * @param bloodStock The data of the blood stock object you are trying to create and store into the HashMap
     * @return The created blood stock wrapped around an ApiResponse
     */
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<BloodStock>> createBloodStock(@RequestBody BloodStock bloodStock) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Content-Type", "application/json")
                .body(
                        ApiResponse.<BloodStock>builder()
                                .message("Blood stock created successfully.")
                                .status(HttpStatus.CREATED)
                                .data(bloodStockService.createBloodStock(bloodStock))
                                .timestamp(new Timestamp(System.currentTimeMillis()))
                                .build()
                );
    }

    /**
     * PUT api/v1/blood-stocks/{id}
     * @param id The id of the blood stock you are looking to update within the HashMap
     * @param bloodStock The data of the blood stock you are going to overwrite with the existing blood stock
     * @return The updated blood stock wrapped around an ApiResponse
     */
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<BloodStock>> updateBloodStock(
            @PathVariable(value = "id") Long id,
            @RequestBody BloodStock bloodStock
    ) {
        if (bloodStockService.getBloodStockById(id) == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .header("Content-Type", "application/json")
                    .body(
                            ApiResponse.<BloodStock>builder()
                                    .message("Blood stock does not exist.")
                                    .status(HttpStatus.NOT_FOUND)
                                    .data(null)
                                    .timestamp(new Timestamp(System.currentTimeMillis()))
                                    .build()
                    );
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(
                        ApiResponse.<BloodStock>builder()
                                .message("Blood stock updated successfully.")
                                .status(HttpStatus.OK)
                                .data(bloodStockService.updateBloodStock(id, bloodStock))
                                .timestamp(new Timestamp(System.currentTimeMillis()))
                                .build()
                );
    }


    /**
     * DELETE api/v1/blood-stocks/{id}
     * @param id The id of the blood stock you are looking to delete
     * @return True if the blood stock was deleted, False otherwise
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> deleteBloodStock(@PathVariable(value = "id") Long id) {
        if (bloodStockService.getBloodStockById(id) == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(false);
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(bloodStockService.deleteBloodStock(id));
        }
    }
}
