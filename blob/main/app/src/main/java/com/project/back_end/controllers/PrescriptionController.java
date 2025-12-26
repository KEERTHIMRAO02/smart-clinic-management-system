package com.project.back_end.controllers;

import com.project.back_end.models.Prescription;
import com.project.back_end.services.PrescriptionService;
import com.project.back_end.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    private final TokenService tokenService;

    public PrescriptionController(PrescriptionService prescriptionService,
                                  TokenService tokenService) {
        this.prescriptionService = prescriptionService;
        this.tokenService = tokenService;
    }

    /**
     * Save a prescription
     */
    @PostMapping("/save")
    public ResponseEntity<?> savePrescription(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody Prescription prescription
    ) {
        if (!tokenService.validateToken(token)) {
            return ResponseEntity.status(401)
                    .body("Invalid or expired token");
        }

        Prescription savedPrescription =
                prescriptionService.savePrescription(prescription);

        return ResponseEntity.ok(savedPrescription);
    }
}
