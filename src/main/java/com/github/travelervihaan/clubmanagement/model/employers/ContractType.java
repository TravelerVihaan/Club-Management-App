package com.github.travelervihaan.clubmanagement.model.employers;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="contracts")
public class ContractType implements Serializable {

    private static final long serialVersionUID = 7021150458271420832L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="contract_id")
    Long id;
    @NotNull
    @Column(unique = true)
    String contractType;

    public ContractType(){}

    public ContractType(@NotNull String contractType) {
        this.contractType = contractType;
    }

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

    @Override
    public String toString() {
        return "ContractType{" +
                "id=" + id +
                ", contractType='" + contractType + '\'' +
                '}';
    }
}
