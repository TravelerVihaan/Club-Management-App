package com.github.travelervihaan.clubmanagement.model.employers;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "employee_details")
public class EmployeeDetails implements Serializable {

    private static final long serialVersionUID = 7021150458271420830L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee_details")
    private Long id;
    //Date hireDay
    //TODO
    private double salary;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_title")
    private JobTitle jobTitle;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="contract_id")
    private ContractType contractType;

    public EmployeeDetails(){}

    public EmployeeDetails(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Long getId() {
        return id;
    }

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
                ", jobTitle=" + jobTitle +
                ", contractType=" + contractType +
                '}';
    }
}
