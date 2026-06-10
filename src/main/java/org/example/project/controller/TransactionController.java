package org.example.project.controller;

import org.example.project.dto.TransactionResponseDto;
import org.example.project.dto.TransferRequest;
import org.example.project.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<TransactionResponseDto> transfer(@RequestBody TransferRequest request) {
        return ResponseEntity.ok(transactionService.transfer(request));
    }

    @GetMapping("/history")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Page<TransactionResponseDto>> getHistory(
            @RequestParam(required = false) Long accountId,
            Pageable pageable) {

        // Nếu không truyền accountId thì dùng account của user hiện tại
        if (accountId == null) {
            return ResponseEntity.ok(transactionService.getTransactionHistory(accountId, pageable));
        }
        return ResponseEntity.ok(transactionService.getTransactionHistory(accountId, pageable));
    }
}