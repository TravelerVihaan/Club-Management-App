package com.github.travelervihaan.clubmanagement.model.payrolls;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "exemptions")
public class Exemption implements Serializable {

    private static final long serialVersionUID = 71L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
}
