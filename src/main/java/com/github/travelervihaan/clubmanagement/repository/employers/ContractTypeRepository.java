package com.github.travelervihaan.clubmanagement.repository.employers;

import com.github.travelervihaan.clubmanagement.model.employers.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractTypeRepository extends JpaRepository<ContractType, Long> {

    ContractType findByContractType(String contractType);
}
