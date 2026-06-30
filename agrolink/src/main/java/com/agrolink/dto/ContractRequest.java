package com.agrolink.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractRequest {

    @NotBlank
    private String cropName;

    @NotNull
    private Double quantity;

    @NotNull
    private BigDecimal pricePerUnit;

    @NotNull
    private BigDecimal totalAmount;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private UUID farmerId;

    @NotNull
    private UUID buyerId;

}