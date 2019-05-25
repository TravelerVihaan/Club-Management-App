package com.github.travelervihaan.clubmanagement.model.absences;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="abscences")
public class AbsenceType implements Serializable {

    private static final long serialVersionUID = 13L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_absence")
    private Long id;

    private String abscenceType;


    public AbsenceType(){}
    public AbsenceType(String abscenceType) {
        this.abscenceType = abscenceType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbscenceType() {
        return abscenceType;
    }

    public void setAbscenceType(String abscenceType) {
        this.abscenceType = abscenceType;
    }

    @Override
    public String toString() {
        return "AbsenceType{" +
                "id=" + id +
                ", abscenceType='" + abscenceType + '\'' +
                '}';
    }
}
