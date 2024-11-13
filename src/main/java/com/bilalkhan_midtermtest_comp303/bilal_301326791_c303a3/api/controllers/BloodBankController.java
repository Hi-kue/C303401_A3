package com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.api.controllers;

import com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.models.BloodBank;
import com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.service.BloodBankService;
import com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("api/v1/blood-banks")
public class BloodBankController {
    private final BloodBankService bloodBankService;

    @Autowired
    public BloodBankController(BloodBankService bloodBankService)  {
        this.bloodBankService = bloodBankService;
    }

    /**
     * GET api/v1/blood-banks
     * @return A list of all the blood stocks wrapped around an ApiResponse
     */
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BloodBank>> getAllBloodBanks() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bloodBankService.getAllBloodBanks());
    }

    /**
     * GET api/v1/blood_banks/{id}
     * @param id  The id of the blood bank to be retrieved
     * @return The blood bank wrapped around an ApiResponse
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<BloodBank>> getBloodBankById(
            @PathVariable(value = "id") Long id
    ) {
        BloodBank bloodBank = bloodBankService.getBloodBankById(id);

        if (bloodBank == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            ApiResponse.<BloodBank>builder()
                                    .message(String.format("Blood bank entity with id: %d not found", id))
                                    .status(HttpStatus.NOT_FOUND)
                                    .data(null)
                                    .timestamp(new Timestamp(System.currentTimeMillis()))
                                    .build()
                    );
        }

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(
                        ApiResponse.<BloodBank>builder()
                                .message(String.format("Blood bank entity with id: %d found", id))
                                .status(HttpStatus.FOUND)
                                .data(bloodBank)
                                .timestamp(new Timestamp(System.currentTimeMillis()))
                                .build()
                );
    }

    /**
     * POST api/v1/blood-stocks
     * @param bloodBank The data of the blood bank object you are trying to create and store into the HashMap
     * @return The created blood bank wrapped around an ApiResponse
     */
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<BloodBank>> createBloodBank(@RequestBody BloodBank bloodBank) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Content-Type", "application/json")
                .body(
                        ApiResponse.<BloodBank>builder()
                                .message("Blood bank entity created successfully.")
                                .status(HttpStatus.CREATED)
                                .data(bloodBankService.createBloodBank(bloodBank))
                                .timestamp(new Timestamp(System.currentTimeMillis()))
                                .build()
                );
    }

    /**
     * PUT api/v1/blood-stocks/{id}
     * @param id The id of the blood bank you are looking to update within the HashMap
     * @param bloodBank The data of the blood bank you are going to overwrite with the existing blood bank
     * @return The updated blood bank wrapped around an ApiResponse
     */
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<BloodBank>> updateBloodBank(
            @PathVariable(value = "id") Long id,
            @RequestBody BloodBank bloodBank
    ) {
        if (bloodBankService.getBloodBankById(id) == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .header("Content-Type", "application/json")
                    .body(
                            ApiResponse.<BloodBank>builder()
                                    .message("Blood bank entity does not exist.")
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
                        ApiResponse.<BloodBank>builder()
                                .message("Blood stock updated successfully.")
                                .status(HttpStatus.OK)
                                .data(bloodBankService.updateBloodBank(id, bloodBank))
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
    public ResponseEntity<Boolean> deleteBloodBank(@PathVariable(value = "id") Long id) {
        if (bloodBankService.getBloodBankById(id) == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(false);
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(bloodBankService.deleteBloodBank(id));
        }
    }

}
