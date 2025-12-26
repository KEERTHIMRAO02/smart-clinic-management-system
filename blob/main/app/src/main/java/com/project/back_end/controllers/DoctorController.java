package com.project.back_end.controllers;

import com.project.back_end.services.DoctorService;
import com.project.back_end.services.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final TokenService tokenService;

    public DoctorController(DoctorService doctorService, TokenService tokenService) {
        this.doctorService = doctorService;
        this.tokenService = tokenService;
    }

    /**
     * Get available time slots for a doctor on a specific date
     */
    @GetMapping("/{doctorId}/availability")
    public ResponseEntity<?> getDoctorAvailability(
            @RequestHeader("Authorization") String token,
            @RequestParam String role,
            @PathVariable Long doctorId,
            @RequestParam String date
    ) {
        if (!tokenService.validateToken(token)) {
            return ResponseEntity.status(401)
                    .body("Invalid or expired token");
        }

        LocalDate localDate = LocalDate.parse(date);

        return ResponseEntity.ok(
                doctorService.getAvailableTimes(doctorId, localDate, role)
        );
    }
}
