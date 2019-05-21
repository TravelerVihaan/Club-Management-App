package com.github.travelervihaan.clubmanagement.service.employers;

import com.github.travelervihaan.clubmanagement.model.employers.ContractType;
import com.github.travelervihaan.clubmanagement.repository.employers.ContractTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractTypeService {

    private ContractTypeRepository contractTypeRepository;

    ContractTypeService(ContractTypeRepository contractTypeRepository){
        this.contractTypeRepository = contractTypeRepository;
    }

    public List<ContractType> getAllContracts(){
        return contractTypeRepository.findAll();
    }

}
