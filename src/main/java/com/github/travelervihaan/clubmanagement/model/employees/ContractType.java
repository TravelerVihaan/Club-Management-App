package com.github.travelervihaan.clubmanagement.model.employees;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="contracts")
public class ContractType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_contract")
    private Long id;
    @NotEmpty
    @Column(name = "contract_type", nullable = false, unique = true)
    private String contractType;

    @OneToMany(mappedBy = "contractType")
    private List<EmployeeDetails> employeeDetails;

    public ContractType(){}

    public ContractType(@NotNull String contractType) { this.contractType = contractType; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public List<EmployeeDetails> getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(List<EmployeeDetails> employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    @Override
    public String toString() {
        return "ContractType{" +
                "id=" + id +
                ", contractType='" + contractType + '\'' +
                '}';
    }
}
