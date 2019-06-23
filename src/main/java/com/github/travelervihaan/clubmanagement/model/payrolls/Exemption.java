package com.github.travelervihaan.clubmanagement.model.payrolls;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "exemptions")
public class Exemption implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
