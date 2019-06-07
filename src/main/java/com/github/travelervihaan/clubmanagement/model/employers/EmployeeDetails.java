package com.github.travelervihaan.clubmanagement.model.employers;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "employers_details")
public class EmployeeDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee_details")
    private Long id;
    //Date hireDay
    //TODO
    private Double salary;
    @ManyToOne
    @JoinColumn(name="title_id")
    private JobTitle jobTitle;
    @ManyToOne
    @JoinColumn(name="contract_id")
    private ContractType contractType;

    public EmployeeDetails(){}

    public EmployeeDetails(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Long getId() {
        return id;
    }

    public Double getSalary() { return salary; }

    public void setSalary(Double salary) { this.salary = salary; }

    public void setId(Long id) {
        this.id = id;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    @Override
    public String toString() {
        return "EmployeeDetails{" +
                "id=" + id +
                ", salary=" + salary +
                ", jobTitle=" + jobTitle +
                ", contractType=" + contractType +
                '}';
    }
}
