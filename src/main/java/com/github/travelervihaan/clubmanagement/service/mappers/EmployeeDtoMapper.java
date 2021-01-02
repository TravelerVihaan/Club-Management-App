package com.github.travelervihaan.clubmanagement.service.mappers;

import com.github.travelervihaan.clubmanagement.dto.EmployeeDto;
import com.github.travelervihaan.clubmanagement.model.employees.ContractType;
import com.github.travelervihaan.clubmanagement.model.employees.Employee;
import com.github.travelervihaan.clubmanagement.model.employees.EmployeeDetails;
import com.github.travelervihaan.clubmanagement.model.employees.JobTitle;
import com.github.travelervihaan.clubmanagement.service.employees.ContractTypeService;
import com.github.travelervihaan.clubmanagement.service.employees.JobTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDtoMapper {

    private JobTitleService jobTitleService;
    private ContractTypeService contractTypeService;

    @Autowired
    public EmployeeDtoMapper(JobTitleService jobTitleService, ContractTypeService contractTypeService) {
        this.jobTitleService = jobTitleService;
        this.contractTypeService = contractTypeService;
    }

    public Employee convertFromDtoToEntity(EmployeeDto employeeDto){
        Employee employee = convertEmployee(employeeDto);
        employee.setEmployeeDetails(convertEmployeeDetails(employeeDto));
        employee.getEmployeeDetails().setJobTitle(convertJobTitle(employeeDto));
        employee.getEmployeeDetails().setContractType(convertContractType(employeeDto));
        return employee;
    }

    private Employee convertEmployee(EmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setUsername(employeeDto.getUsername());
        employee.setPassword(employeeDto.getPassword());
        employee.setName(employeeDto.getName());
        employee.setSurname(employeeDto.getSurname());
        employee.setEmail(employeeDto.getEmail());
        return employee;
    }

    private EmployeeDetails convertEmployeeDetails(EmployeeDto employeeDto){
        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setDayOfHireTerminate(employeeDto.getDayOfHireTerminate());
        employeeDetails.setSalary(employeeDetails.getSalary());
        return employeeDetails;
    }

    private JobTitle convertJobTitle(EmployeeDto employeeDto){
        return jobTitleService.getJobTitleByTitle(employeeDto.getJobTitle());
    }

    private ContractType convertContractType(EmployeeDto employeeDto){
        return contractTypeService.getContractTypeByType(employeeDto.getContractType());
    }
}
