package com.agrolink.service;

import com.agrolink.dto.ContractRequest;
import com.agrolink.entity.Contract;
import com.agrolink.entity.User;
import com.agrolink.enums.ContractStatus;
import com.agrolink.repository.ContractRepository;
import com.agrolink.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final UserRepository userRepository;

    public Contract createContract(ContractRequest request) {

        User farmer = userRepository.findById(request.getFarmerId())
                .orElseThrow(() -> new RuntimeException("Farmer not found"));

        User buyer = userRepository.findById(request.getBuyerId())
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        Contract contract = Contract.builder()
                .cropName(request.getCropName())
                .quantity(request.getQuantity())
                .pricePerUnit(request.getPricePerUnit())
                .totalAmount(request.getTotalAmount())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .farmer(farmer)
                .buyer(buyer)
                .status(ContractStatus.PENDING)
                .paymentCompleted(false)
                .deleted(false)
                .build();

        return contractRepository.save(contract);
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findByDeletedFalse();
    }

    public Contract getContractById(UUID id) {

        return contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

    }

    public List<Contract> getContractsByFarmer(UUID farmerId) {

        User farmer = userRepository.findById(farmerId)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));

        return contractRepository.findByFarmer(farmer);
    }

    public List<Contract> getContractsByBuyer(UUID buyerId) {

        User buyer = userRepository.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        return contractRepository.findByBuyer(buyer);
    }

    public Contract acceptContract(UUID id) {

        Contract contract = getContractById(id);

        contract.setStatus(ContractStatus.ACCEPTED);

        return contractRepository.save(contract);
    }

    public Contract rejectContract(UUID id) {

        Contract contract = getContractById(id);

        contract.setStatus(ContractStatus.REJECTED);

        return contractRepository.save(contract);
    }

    public Contract completeContract(UUID id) {

        Contract contract = getContractById(id);

        contract.setStatus(ContractStatus.COMPLETED);
        contract.setPaymentCompleted(true);

        return contractRepository.save(contract);
    }

    public void deleteContract(UUID id) {

        Contract contract = getContractById(id);

        contract.setDeleted(true);

        contractRepository.save(contract);
    }

}