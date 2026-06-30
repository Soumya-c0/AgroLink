package com.agrolink.entity;

import com.agrolink.enums.ContractStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "contracts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String cropName;

    @Column(nullable = false)
    private Double quantity;

    @Column(nullable = false)
    private BigDecimal pricePerUnit;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ContractStatus status = ContractStatus.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id")
    private User farmer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @Builder.Default
    private boolean paymentCompleted = false;

    @Builder.Default
    private boolean deleted = false;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}