package com.github.travelervihaan.clubmanagement.service.employers;

import com.github.travelervihaan.clubmanagement.repository.employers.ContractTypeRepository;
import com.github.travelervihaan.clubmanagement.repository.employers.EmployeeDetailsRepository;
import com.github.travelervihaan.clubmanagement.repository.employers.EmployeeRepository;
import com.github.travelervihaan.clubmanagement.repository.employers.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddEmployeeService {

    private EmployeeRepository employeeRepository;
    private ContractTypeRepository contractTypeRepository;
    private JobTitleRepository jobTitleRepository;
    private EmployeeDetailsRepository employeeDetailsRepository;

    @Autowired
    AddEmployeeService(EmployeeRepository employeeRepository,
                       ContractTypeRepository contractTypeRepository,
                       JobTitleRepository jobTitleRepository,
                       EmployeeDetailsRepository employeeDetailsRepository){
        this.employeeRepository = employeeRepository;
        this.contractTypeRepository = contractTypeRepository;
        this.jobTitleRepository = jobTitleRepository;
        this.employeeDetailsRepository = employeeDetailsRepository;
    }
}
