package com.agrolink.repository;

import com.agrolink.entity.Contract;
import com.agrolink.entity.User;
import com.agrolink.enums.ContractStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContractRepository extends JpaRepository<Contract, UUID> {

    List<Contract> findByFarmer(User farmer);

    List<Contract> findByBuyer(User buyer);

    List<Contract> findByStatus(ContractStatus status);

    List<Contract> findByDeletedFalse();

}