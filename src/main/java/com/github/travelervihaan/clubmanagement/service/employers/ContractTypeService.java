package com.github.travelervihaan.clubmanagement.service.employers;

import com.github.travelervihaan.clubmanagement.model.employers.ContractType;
import com.github.travelervihaan.clubmanagement.repository.employers.ContractTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;

@Service
public class ContractTypeService {

    private ContractTypeRepository contractTypeRepository;

    @Autowired
    ContractTypeService(ContractTypeRepository contractTypeRepository){
        this.contractTypeRepository = contractTypeRepository;
    }

    public List<ContractType> getAllContracts(){
        return contractTypeRepository.findAll();
    }

    public ContractType getContractTypeByType(String contractType){
        return contractTypeRepository.findByContractType(contractType).orElseThrow();
    }

    public void deleteContractType(String contractTypeName){
        if(contractTypeRepository.findByContractType(contractTypeName).isPresent())
            contractTypeRepository.delete(contractTypeRepository.findByContractType(contractTypeName).get());
    }

    public void addNewContractType(@Valid ContractType contractType, BindingResult result){
        if(!result.hasErrors()) {
            if (contractTypeRepository.findByContractType(contractType.getContractType()).isEmpty())
                contractTypeRepository.save(contractType);
        }
    }

}
