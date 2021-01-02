package com.github.travelervihaan.clubmanagement.repository.employees;

import com.github.travelervihaan.clubmanagement.model.employees.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractTypeRepository extends JpaRepository<ContractType, Long> {

    Optional<ContractType> findByContractType(String contractType);
}
