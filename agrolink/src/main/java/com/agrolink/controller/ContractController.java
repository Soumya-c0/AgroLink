package com.agrolink.controller;

import com.agrolink.dto.ContractRequest;
import com.agrolink.entity.Contract;
import com.agrolink.service.ContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ContractController {

    private final ContractService contractService;

    @PostMapping
    public ResponseEntity<Contract> createContract(
            @Valid @RequestBody ContractRequest request) {

        Contract contract = contractService.createContract(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(contract);
    }

    @GetMapping
    public ResponseEntity<List<Contract>> getAllContracts() {

        return ResponseEntity.ok(contractService.getAllContracts());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Contract> getContractById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(contractService.getContractById(id));

    }

    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<Contract>> getFarmerContracts(
            @PathVariable UUID farmerId) {

        return ResponseEntity.ok(
                contractService.getContractsByFarmer(farmerId)
        );

    }

    @GetMapping("/buyer/{buyerId}")
    public ResponseEntity<List<Contract>> getBuyerContracts(
            @PathVariable UUID buyerId) {

        return ResponseEntity.ok(
                contractService.getContractsByBuyer(buyerId)
        );

    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<Contract> acceptContract(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                contractService.acceptContract(id)
        );

    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<Contract> rejectContract(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                contractService.rejectContract(id)
        );

    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Contract> completeContract(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                contractService.completeContract(id)
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContract(
            @PathVariable UUID id) {

        contractService.deleteContract(id);

        return ResponseEntity.ok("Contract deleted successfully.");

    }

}