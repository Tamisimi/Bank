package org.example.project.controller;

import org.example.project.service.KycService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/kyc")
public class KycController {

    private final KycService kycService;

    public KycController(KycService kycService) {
        this.kycService = kycService;
    }

    @PostMapping("/upload")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<String> uploadKyc(
            @RequestParam("front") MultipartFile front,
            @RequestParam("back") MultipartFile back) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (front.isEmpty() || back.isEmpty()) {
            return ResponseEntity.badRequest().body("Both front and back images are required");
        }

        String result = kycService.uploadKyc(username, front, back);
        return ResponseEntity.ok(result);
    }
}