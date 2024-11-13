package com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.api.controllers;

import com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.models.Seeker;
import com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.service.SeekerService;
import com.bilalkhan_midtermtest_comp303.bilal_301326791_c303a3.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("api/v1/seekers")
public class SeekerController {
    private final SeekerService seekerService;

    @Autowired
    public SeekerController(SeekerService seekerService) {
        this.seekerService = seekerService;
    }

    /**
     * GET api/v1/seekers
     * @return A list of all seekers wrapped around an ApiResponse
     */
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<List<Seeker>>> getAllSeekers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(
                        ApiResponse.<List<Seeker>>builder()
                                .message("All seekers retrieved successfully.")
                                .status(HttpStatus.OK)
                                .data(seekerService.getAllSeekers())
                                .timestamp(new Timestamp(System.currentTimeMillis()))
                                .build()
                );
    }

    /**
     * GET api/v1/seekers/{id}
     * @param id The id of the seeker you are looking to retrieve from the HashMap
     * @return The seeker wrapped around an ApiResponse
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<Seeker>> getSeekerById(@PathVariable(value = "id") Long id) {
        if (seekerService.getSeekerBySeekerId(id) == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .header("Content-Type", "application/json")
                    .body(
                            ApiResponse.<Seeker>builder()
                                    .message("Seeker does not exist.")
                                    .status(HttpStatus.NOT_FOUND)
                                    .data(seekerService.getSeekerBySeekerId(id))
                                    .timestamp(new Timestamp(System.currentTimeMillis()))
                                    .build()
                    );
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(
                        ApiResponse
                                .<Seeker>builder()
                                .message("Seeker retrieved successfully.")
                                .status(HttpStatus.OK)
                                .data(seekerService.getSeekerBySeekerId(id))
                                .timestamp(new Timestamp(System.currentTimeMillis()))
                                .build()
                );
    }

    /**
     * POST api/v1/seekers
     * @param seeker The data of the seeker object you are trying to create and store into the HashMap
     * @return The created seeker wrapped around an ApiResponse
     */
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Seeker> createSeeker(@RequestBody Seeker seeker) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Content-Type", "application/json")
                .body(seekerService.createSeeker(seeker));
    }

    /**
     * PUT api/v1/seekers/{id}
     * @param id The id of the seeker you are looking to update within the HashMap
     * @param seeker The data of the seeker you are going to overwrite with the existing seeker
     * @return The updated seeker wrapped around an ApiResponse
     */
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<Seeker>> updateSeeker(
            @PathVariable(value = "id") Long id,
            @RequestBody Seeker seeker
    ) {
        Timestamp currentTime  = new Timestamp(System.currentTimeMillis());

        if (seekerService.getSeekerBySeekerId(id) == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .header("Content-Type", "application/json")
                    .body(
                            ApiResponse.<Seeker>builder()
                                    .message("Seeker does not exist.")
                                    .status(HttpStatus.NOT_FOUND)
                                    .data(null)
                                    .timestamp(currentTime)
                                    .build()
                    );
        }

        Seeker updatedSeeker = seekerService.updateSeeker(id, seeker);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(
                        ApiResponse.<Seeker>builder()
                                .message("Seeker was updated successfully.")
                                .status(HttpStatus.OK)
                                .data(updatedSeeker)
                                .timestamp(currentTime)
                                .build()
                );
    }

    /**
     * DELETE api/v1/seekers/{id}
     * @param id The id of the seeker you are looking to delete
     * @return True if the seeker was deleted, False otherwise
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> deleteSeeker(@PathVariable(value = "id") Long id) {
        if (seekerService.getSeekerBySeekerId(id) == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(false);
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(seekerService.deleteSeekerById(id));
        }
    }
}
