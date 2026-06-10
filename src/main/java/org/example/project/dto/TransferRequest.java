package org.example.project.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {
    private String targetAccountNumber;
    private BigDecimal amount;
    private String description;
}